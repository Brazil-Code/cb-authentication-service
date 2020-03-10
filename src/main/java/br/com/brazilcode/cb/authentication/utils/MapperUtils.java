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
 * Classe utilitária para parse de objetos de diferentes tipos. Exemplo: DTO ->
 * Entity
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de mar de 2020 22:48:47
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class MapperUtils {

	@Autowired
	private Mapper mapper;

	/**
	 * Método responsável por fazer o parse de um objeto para uma classe target.
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
	 * Método responsável por fazer o parse de um objeto Optional para uma classe
	 * target.
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
	 * Método responsável por fazer o parse de uma Lista de objetos para uma classe
	 * target.
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
	 * Método responsável por fazer o parse de uma lista de objetos optional para
	 * uma classe target.
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
	 * Método responsável por fazer o parse lazy de um objeto para uma classe
	 * target.
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
	 * Método responsável por fazer o parse lazy de um objeto optional para uma
	 * classe target.
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
