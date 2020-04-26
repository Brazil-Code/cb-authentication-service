package br.com.brazilcode.cb.authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Class responsible to enable async methods in the application.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:39:06 AM
 * @version 1.0
 */
@Configuration
@EnableAsync
public class AsyncConfig {

	/**
	 * Método responsável por configurar a disponibilização de um nova Thread para
	 * métodos assíncronos.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 */
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(5);
		pool.setMaxPoolSize(10);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

}
