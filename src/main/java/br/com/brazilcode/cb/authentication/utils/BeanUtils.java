package br.com.brazilcode.cb.authentication.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Utility class for Beans.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 2:02:10 AM
 * @version 1.0
 */
@Component
public class BeanUtils {

	private static BeanUtils instance;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void registerInstance() {
		instance = this;
	}

	public static <T> T getBean(Class<T> clazz) {
		return instance.applicationContext.getBean(clazz);
	}

}