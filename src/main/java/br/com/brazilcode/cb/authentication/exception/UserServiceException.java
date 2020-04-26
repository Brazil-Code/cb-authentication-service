package br.com.brazilcode.cb.authentication.exception;

import br.com.brazilcode.cb.libs.exception.BaseException;

/**
 * Class responsible for creating a customized Exception for User integration errors.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:51:35 AM
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
