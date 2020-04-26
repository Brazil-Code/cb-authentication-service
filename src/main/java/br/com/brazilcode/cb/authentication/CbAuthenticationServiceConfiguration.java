package br.com.brazilcode.cb.authentication;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Class responsible for configurating the application initializations.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 2:12:37 AM
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
