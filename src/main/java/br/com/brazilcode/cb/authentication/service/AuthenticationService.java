package br.com.brazilcode.cb.authentication.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brazilcode.cb.authentication.dto.AccountCredentialsDTO;
import br.com.brazilcode.cb.authentication.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * Class responsible for providing a authentication service applying the business rules.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:58:40 AM
 * @version 1.0
 */
@Component
@Slf4j
public class AuthenticationService {

	@Autowired
	private UserService userService;

	/**
	 * Escopo de método de serviço para implementação do login na aplicação.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param request
	 * @return
	 * @throws AuthenticationException
	 * @throws IOException
	 * @throws ServletException
	 */
	public Authentication login(final HttpServletRequest request) throws AuthenticationException, IOException, ServletException {
		final String method = "[ AuthenticationService.login ] - ";
		log.info(method + "BEGIN");

		final AccountCredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(), AccountCredentialsDTO.class);

		final String username = credentials.getUsername(), password = credentials.getPassword();

		try {
			log.info(method + "Searching for user in database...");
			final UserDTO user = userService.findByUsernameAndPassword(username, password);

			final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
			authToken.setDetails(user);

			log.info(method + "Returning authentication token...");
			return authToken;
		} catch (final Exception e) {
			throw new ServletException(method + "ERROR during authentication", e);
		} finally {
			log.info(method + "END");
		}
	}

}
