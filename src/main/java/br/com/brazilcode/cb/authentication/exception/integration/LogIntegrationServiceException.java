package br.com.brazilcode.cb.authentication.exception.integration;

/**
 * Class responsible for creating a customized Exception for Log Integration errors.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:49:18 AM
 * @version 1.0
 */
public class LogIntegrationServiceException extends Exception {

	private static final long serialVersionUID = 596130936590524127L;

	public LogIntegrationServiceException() {
		super();
	}

	public LogIntegrationServiceException(String message) {
		super(message);
	}

	public LogIntegrationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogIntegrationServiceException(Throwable cause) {
		super(cause);
	}

}
