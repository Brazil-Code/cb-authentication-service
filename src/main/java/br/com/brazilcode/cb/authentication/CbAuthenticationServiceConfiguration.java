package br.com.brazilcode.cb.authentication;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Classe responsável por configurar a inicialização da aplicação.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 17:22:23
 * @version 1.0
 */
@ComponentScan("br.com.brazilcode.cb.authentication.utils")
@Configuration
public class CbAuthenticationServiceConfiguration {

	@Bean
	public Mapper mapper() {
		return new DozerBeanMapper();
	}

}
