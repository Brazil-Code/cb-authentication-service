package br.com.brazilcode.cb.authentication.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 * Utility class for generating encrypted passwords.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 2:03:00 AM
 * @version 1.0
 */
@Component
public class CryptPasswordGeneratorUtils {

	/**
	 * Method responsible for encrypting a String using SHA-256 hash.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public String cryptString(String string) throws Exception {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(string.getBytes("UTF-8"));
			String crypted = new BigInteger(1, messageDigest.digest()).toString(16);

			return crypted;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new Exception("Error while crypting string: " + string);
		}
	}

}
