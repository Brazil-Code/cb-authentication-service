package br.com.brazilcode.cb.authentication.exception;

/**
 * Classe responsável por configurar uma exceção personalizada para o módulo de
 * autenticação.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 15:13:36
 * @version 1.0
 */
public class AuthenticationServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3708602828944966655L;

	public AuthenticationServiceException(final String message) {
		super(message);
	}

	public AuthenticationServiceException(String message, Throwable e) {
		super(message, e);
	}

}
