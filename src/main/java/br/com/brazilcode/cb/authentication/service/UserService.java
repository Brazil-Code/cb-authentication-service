package br.com.brazilcode.cb.authentication.service;

import java.io.Serializable;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brazilcode.cb.authentication.dto.UserDTO;
import br.com.brazilcode.cb.authentication.exception.CbAuthenticationException;
import br.com.brazilcode.cb.authentication.exception.UserServiceException;
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
		LOGGER.debug("[ UserIntegrationService.findByUsernameAndPassword ] - BEGIN");

		try {
			// TODO: Melhorar a criptografia da senha -> Atual: Base64
			LOGGER.debug("[ UserIntegrationService.findByUsernameAndPassword ] - Encrypting password");
			String passwordEncoded = Base64.encodeBase64String(password.getBytes());
			User user = userDAO.findByUsernameAndPassword(username, passwordEncoded);

			if (user == null) {
				LOGGER.error("[ UserIntegrationService.findByUsernameAndPassword ] - Invalid username/password");
				throw new UserServiceException("Invalid username/password");
			}

			UserDTO userDTO = mapperUtils.parse(user, UserDTO.class);

			LOGGER.debug("[ UserIntegrationService.findByUsernameAndPassword ] - User found: " + userDTO.getUsername());
			return userDTO;
		} catch (Exception e) {
			LOGGER.error("[ UserIntegrationService.findByUsernameAndPassword ] - ERROR: ", e);
			throw new UserServiceException("ERROR while searching for user: { " + username + " } in database: ");
		} finally {
			LOGGER.debug("[ UserIntegrationService.findByUsernameAndPassword ] - END");
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
		LOGGER.debug("[ UserService.updateToken ] - BEGIN");
		LOGGER.debug("[ UserService.updateToken ] - User ID: " + user.getId());
		try {
			if (user.getId() != null) {
				userDAO.updateTokenById(user.getToken(), user.getId());
			} else {
				throw new UserServiceException("[ UserService.updateToken ] - No user ID informed");
			}
		} catch (Exception e) {
			LOGGER.error("[ UserService.updateToken ] - ERROR: ", e);
			throw new UserServiceException("[ UserService.updateToken ] - ERROR while updating user: " + user);
		} finally {
			LOGGER.debug("[ UserService.updateToken ] - END");
		}
	}

}
