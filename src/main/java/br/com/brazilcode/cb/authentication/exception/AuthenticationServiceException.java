package br.com.brazilcode.cb.authentication.exception;

/**
 * Class responsible for creating a customized Execption for Authentication errors.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:50:49 AM
 * @version 1.0
 */
public class AuthenticationServiceException extends Exception {

	private static final long serialVersionUID = -3708602828944966655L;

	public AuthenticationServiceException(final String message) {
		super(message);
	}

	public AuthenticationServiceException(String message, Throwable e) {
		super(message, e);
	}

}
