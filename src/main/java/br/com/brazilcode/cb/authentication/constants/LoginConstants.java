package br.com.brazilcode.cb.authentication.constants;

/**
 * Classe responsável por armazenar as informações configuração de login da
 * aplicação.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 14:42:24
 * @version 1.0
 */
public class LoginConstants {

	public final static long EXPIRATIONTIME = 86_400_000; // 1 dia
	public final static String SECRET = "518396A4A9DC4536E56BD17D91C31";
	public final static String TOKEN_PREFIX = "Bearer";
	public final static String HEADER_STRING = "Authorization";
	public final static String LOGIN_URI = "/login";
	public final static String SERVER_DATE_URI = "/serverStartTime";
	public final static String USER_ID = "userId";

}
