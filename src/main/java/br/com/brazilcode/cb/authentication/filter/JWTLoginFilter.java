package br.com.brazilcode.cb.authentication.filter;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brazilcode.cb.authentication.constants.LoginConstants;
import br.com.brazilcode.cb.authentication.dto.UserDTO;
import br.com.brazilcode.cb.authentication.service.AuthenticationService;
import br.com.brazilcode.cb.authentication.service.UserService;
import br.com.brazilcode.cb.authentication.utils.BeanUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe responsável por aplicar o filtro de login com autenticação JWT.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 14:46:28
 * @version 1.0
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTLoginFilter.class);
	private AuthenticationService authenticationService;
	private UserService userIntegrationService;

	/**
	 * Construtor da classe JWTLoginFilter
	 *
	 * @param url
	 * @param authManager
	 */
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.authenticationService = BeanUtils.getBean(AuthenticationService.class);
		this.userIntegrationService = BeanUtils.getBean(UserService.class);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		return this.authenticationService.login(request);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {
		final String method = "[ JWTLoginFilter.successfulAuthentication ] - ";
		try {
			LOGGER.debug(method + "Successful Authentication");
			UserDTO user = (UserDTO) auth.getDetails();
			String JWT = Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(auth.getName())
					.claim(LoginConstants.USER_ID, user.getId())
					.setExpiration(new Date(System.currentTimeMillis() + LoginConstants.EXPIRATIONTIME))
					.signWith(SignatureAlgorithm.HS512, LoginConstants.SECRET).compact();

			user.setToken(LoginConstants.TOKEN_PREFIX + " " + JWT);
			response.addHeader(LoginConstants.HEADER_STRING, user.getToken());

			LOGGER.debug(method + "Updating user token");
			this.userIntegrationService.updateToken(user);

			response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(user));
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		} catch (final Exception e) {
			LOGGER.error(method + "ERROR: ", e);
			throw new ServletException("ERROR during authentication", e);
		}
	}

}
