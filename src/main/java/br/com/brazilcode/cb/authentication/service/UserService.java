package br.com.brazilcode.cb.authentication.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brazilcode.cb.authentication.dto.UserDTO;
import br.com.brazilcode.cb.authentication.exception.UserServiceException;
import br.com.brazilcode.cb.authentication.utils.CryptPasswordGeneratorUtils;
import br.com.brazilcode.cb.authentication.utils.MapperUtils;
import br.com.brazilcode.cb.libs.model.User;
import br.com.brazilcode.cb.libs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Class responsible for applying the business rules for the Users services.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 2:00:10 AM
 * @version 1.0
 */
@Service
@Slf4j
public class UserService implements Serializable {

	private static final long serialVersionUID = -1667927672586384315L;

	@Autowired
	private UserRepository userDAO;

	@Autowired
	private MapperUtils mapperUtils;

	@Autowired
	private CryptPasswordGeneratorUtils cryptUtils;

	/**
	 * Method responsible for searching for a {@link User} by the given username and password.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param username
	 * @param password
	 * @return
	 * @throws UserServiceException
	 */
	public UserDTO findByUsernameAndPassword(String username, String password) throws UserServiceException {
		final String method = "[ UserIntegrationService.findByUsernameAndPassword ] - ";
		log.info(method + "BEGIN");

		try {
			log.info(method + "Encrypting password");
			String passwordEncoded = cryptUtils.cryptString(password);
			User user = userDAO.findByUsernameAndPassword(username, passwordEncoded);

			if (user == null) {
				log.error(method + "Invalid username/password");
				throw new UserServiceException("Invalid username/password");
			}

			UserDTO userDTO = mapperUtils.parse(user, UserDTO.class);

			log.info(method + "User found: " + userDTO.getUsername());
			return userDTO;
		} catch (Exception e) {
			log.error(method + "ERROR: ", e);
			throw new UserServiceException("ERROR while searching for user: { " + username + " } in database: ");
		} finally {
			log.info(method + "END");
		}
	}

	/**
	 * Method responsible for updating the {@link User}'s auth token.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param user
	 * @throws UserServiceException
	 */
	public void updateToken(UserDTO user) throws UserServiceException {
		final String method = "[ UserService.updateToken ] - ";
		log.info(method + "BEGIN");
		log.info(method + "User ID: " + user.getId());
		try {
			if (user.getId() != null) {
				userDAO.updateTokenById(user.getToken(), user.getId());
			} else {
				throw new UserServiceException(method + "No user ID informed");
			}
		} catch (Exception e) {
			log.error(method + "ERROR: ", e);
			throw new UserServiceException(method + "ERROR while updating user: " + user);
		} finally {
			log.info(method + "END");
		}
	}

}
