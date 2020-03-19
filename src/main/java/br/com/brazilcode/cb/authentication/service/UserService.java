package br.com.brazilcode.cb.authentication.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brazilcode.cb.authentication.dto.UserDTO;
import br.com.brazilcode.cb.authentication.exception.CbAuthenticationException;
import br.com.brazilcode.cb.authentication.exception.UserServiceException;
import br.com.brazilcode.cb.authentication.utils.CryptPasswordGeneratorUtils;
import br.com.brazilcode.cb.authentication.utils.MapperUtils;
import br.com.brazilcode.cb.libs.model.User;
import br.com.brazilcode.cb.libs.repository.UserRepository;

/**
 * Classe responsável por aplicar as regras de negócio para os serviços de
 * usuário.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 15:04:13
 * @version 1.0
 */
@Service
public class UserService implements Serializable {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = -1667927672586384315L;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userDAO;

	@Autowired
	private MapperUtils mapperUtils;

	@Autowired
	private CryptPasswordGeneratorUtils cryptUtils;

	/**
	 * Método responsável por buscar um usuário no banco de dados pelo username e
	 * password.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param username
	 * @param password
	 * @return
	 * @throws CbAuthenticationException
	 * @throws UserServiceException
	 */
	public UserDTO findByUsernameAndPassword(String username, String password) throws UserServiceException {
		final String method = "[ UserIntegrationService.findByUsernameAndPassword ] - ";
		LOGGER.debug(method + "BEGIN");

		try {
			LOGGER.debug(method + "Encrypting password");
			String passwordEncoded = cryptUtils.cryptString(password);
			User user = userDAO.findByUsernameAndPassword(username, passwordEncoded);

			if (user == null) {
				LOGGER.error(method + "Invalid username/password");
				throw new UserServiceException("Invalid username/password");
			}

			UserDTO userDTO = mapperUtils.parse(user, UserDTO.class);

			LOGGER.debug(method + "User found: " + userDTO.getUsername());
			return userDTO;
		} catch (Exception e) {
			LOGGER.error(method + "ERROR: ", e);
			throw new UserServiceException("ERROR while searching for user: { " + username + " } in database: ");
		} finally {
			LOGGER.debug(method + "END");
		}
	}

	/**
	 * Método responsável por atualizar o token de autenticação do usuário.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @throws IsaAuthenticationException
	 * @throws UsuarioServiceException
	 */
	public void updateToken(UserDTO user) throws UserServiceException {
		final String method = "[ UserService.updateToken ] - ";
		LOGGER.debug(method + "BEGIN");
		LOGGER.debug(method + "User ID: " + user.getId());
		try {
			if (user.getId() != null) {
				userDAO.updateTokenById(user.getToken(), user.getId());
			} else {
				throw new UserServiceException(method + "No user ID informed");
			}
		} catch (Exception e) {
			LOGGER.error(method + "ERROR: ", e);
			throw new UserServiceException(method + "ERROR while updating user: " + user);
		} finally {
			LOGGER.debug(method + "END");
		}
	}

}
