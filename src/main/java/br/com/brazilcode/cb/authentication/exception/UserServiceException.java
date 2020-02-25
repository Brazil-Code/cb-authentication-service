package br.com.brazilcode.cb.authentication.exception;

import br.com.brazilcode.cb.libs.exception.BaseException;

/**
 * Classe responsável por configurar uma exceção personalizada para o serviço de
 * integração com usuários.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 16:13:46
 * @version 1.0
 */
public class UserServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserServiceException(String errorCode, String message, String instruction) {
		super(errorCode, message, instruction);
	}

	public UserServiceException(String message) {
		super(message);
	}

}
