package br.com.brazilcode.cb.authentication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.brazilcode.cb.authentication.constants.LoginConstants;
import br.com.brazilcode.cb.authentication.filter.JWTAuthenticationTokenParserFilter;
import br.com.brazilcode.cb.authentication.filter.JWTLoginFilter;

/**
 * Classe responsável por habilitar as configurações de segurança da aplicação.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 13:16:02
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTAuthenticationTokenParserFilter authenticationFilter;

	private static final String[] AUTH_WHITELIST = {
	        "/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll()
				.antMatchers(HttpMethod.POST, LoginConstants.LOGIN_URI).permitAll()
				.antMatchers(HttpMethod.GET, LoginConstants.SERVER_DATE_URI).permitAll()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated()
				.and()
				// Filtrando requisicoes api/login
				.addFilterBefore(new JWTLoginFilter(LoginConstants.LOGIN_URI, authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// Filtrando todas as requisicoes para verificar a presenca do JWT no header
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
