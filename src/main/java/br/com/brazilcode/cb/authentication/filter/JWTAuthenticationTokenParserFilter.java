package br.com.brazilcode.cb.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.com.brazilcode.cb.authentication.constants.LoginConstants;
import br.com.brazilcode.cb.authentication.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe responsável por aplicar o filtro de verificação do JWT nas requisições.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 13:28:45
 * @version 1.0
 */
@Component
@Slf4j
public class JWTAuthenticationTokenParserFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest servletRequest = (HttpServletRequest) request;
		final String token = servletRequest.getHeader(LoginConstants.HEADER_STRING);

		if (token != null) {
			try {
				// Parse token
				Claims claims = Jwts.parser().setSigningKey(LoginConstants.SECRET)
						.parseClaimsJws(token.replace(LoginConstants.TOKEN_PREFIX, "")).getBody();
				if (claims != null) {
					final String subject = claims.getSubject();

					// Adds the ID and Username on authentication`s object
					final UserDTO user = new UserDTO();
					user.setId(((Integer) claims.get(LoginConstants.USER_ID)).longValue());
					user.setUsername(subject);
					Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (Exception e) {
				log.error("JWT - Invalid Token: {} - {} - ERROR: {}", token, servletRequest.getRequestURI(), e.getMessage());
			}
		}

		chain.doFilter(request, response);
	}

}
