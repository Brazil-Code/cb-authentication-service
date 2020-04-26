package br.com.brazilcode.cb.authentication.constants;

/**
 * Class responsible for storing the application's login configuration
 * information.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:44:09 AM
 * @version 1.0
 */
public class LoginConstants {

	public final static long EXPIRATIONTIME = 86400000;
	public final static String SECRET = "296372BA7674D7A8D4B4AFAF98823F96FAF0BC3BD1EE4EC88D0555264A335B5D";
	public final static String TOKEN_PREFIX = "Bearer";
	public final static String HEADER_STRING = "Authorization";
	public final static String LOGIN_URI = "/login";
	public final static String SERVER_DATE_URI = "/serverStartTime";
	public final static String USER_ID = "userId";

}
