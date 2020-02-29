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
		LOGGER.debug("[ AuthenticationService.login ] - BEGIN");

		final AccountCredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(),
				AccountCredentialsDTO.class);

		// Recuperando usuário e senha das credenciais da conta
		final String username = credentials.getUsername(), password = credentials.getPassword();

		try {
			LOGGER.debug("[ AuthenticationService.login ] - Searching for user in database...");
			final UserDTO user = userIntegrationService.findByUsernameAndPassword(username, password);

//			final List<SimpleGrantedAuthority> authorities = user.getProfiles().stream()
//					.map(p -> new SimpleGrantedAuthority(p.getDescription())).collect(Collectors.toList());

			final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
					password);
			authToken.setDetails(user);

			LOGGER.debug("[ AuthenticationService.login ] - Returning authentication token...");
			return authToken;
		} catch (final Exception e) {
			throw new ServletException("[ AuthenticationService.login ] - ERROR during authentication", e);
		} finally {
			LOGGER.debug("[ AuthenticationService.login ] - END");
		}
	}

}
