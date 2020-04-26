package br.com.brazilcode.cb.authentication.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Utility class for parsing objects - Egg. DTO -> Entity
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 2:05:07 AM
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class MapperUtils {

	@Autowired
	private Mapper mapper;

	/**
	 * Method responsible for parsing a given object into the given target class.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param <T>
	 * @param object
	 * @param target
	 * @return
	 */
	public <T> T parse(Object object, Class<T> target) {
		return mapper.map(object, target);
	}

	/**
	 * Method responsible for parsing a given object into a Optional object of the given target class.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param <T>
	 * @param object
	 * @param target
	 * @return
	 */
	public <T> Optional<T> parseOptional(Object object, Class<T> target) {
		return Optional.ofNullable(parse(object, target));
	}

	/**
	 * Method responsible for parsing a given List of objects into the given target class.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param <T>
	 * @param listObject
	 * @param target
	 * @return
	 */
	public <T> List<T> parseList(List<?> listObject, Class<T> target) {
		List<T> listRetorno = new ArrayList<T>();
		for (Object object : listObject) {
			T objectTarget = mapper.map(object, target);
			listRetorno.add(objectTarget);
		}
		return listRetorno;
	}

	/**
	 * Method responsible for parsing a given List of objects into a Optional object of the given target class.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param <T>
	 * @param listObject
	 * @param target
	 * @return
	 */
	public <T> Optional<List<T>> parseListOptional(List<?> listObject, Class<T> target) {
		return Optional.ofNullable(parseList(listObject, target));
	}

	/**
	 * Method responsible for lazy parsing a given object into the given target class.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param <T>
	 * @param object
	 * @param target
	 * @return
	 */
	public <T> T parseLazy(Object object, Class<T> target) {
		try {
			final T instance = target.newInstance();
			BeanUtils.copyProperties(instance, object);
			return instance;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Method responsible for lazy parsing a given object into a Optional object of the given target class.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param <T>
	 * @param object
	 * @param target
	 * @return
	 */
	public <T> Optional<T> parseLazyOptional(Object object, Class<T> target) {
		return Optional.ofNullable(parseLazy(object, target));
	}

}
