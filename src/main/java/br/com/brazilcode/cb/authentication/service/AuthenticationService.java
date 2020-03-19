package br.com.brazilcode.cb.authentication.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brazilcode.cb.authentication.dto.AccountCredentialsDTO;
import br.com.brazilcode.cb.authentication.dto.UserDTO;

@Component
public class AuthenticationService {

	/**
	 * Atributo LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

	/**
	 * Atributo usuarioIntegrationService
	 */
	@Autowired
	private UserService userIntegrationService;

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
	public Authentication login(final HttpServletRequest request)
			throws AuthenticationException, IOException, ServletException {
		final String method = "[ AuthenticationService.login ] - ";
		LOGGER.debug(method + "BEGIN");

		final AccountCredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(),
				AccountCredentialsDTO.class);

		// Recuperando usuário e senha das credenciais da conta
		final String username = credentials.getUsername(), password = credentials.getPassword();

		try {
			LOGGER.debug(method + "Searching for user in database...");
			final UserDTO user = userIntegrationService.findByUsernameAndPassword(username, password);

			final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
					password);
			authToken.setDetails(user);

			LOGGER.debug(method + "Returning authentication token...");
			return authToken;
		} catch (final Exception e) {
			throw new ServletException(method + "ERROR during authentication", e);
		} finally {
			LOGGER.debug(method + "END");
		}
	}

}
