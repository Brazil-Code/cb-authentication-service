package br.com.brazilcode.cb.authentication.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 * Classe utilitária para geração de senhas encriptadas.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de mar de 2020 22:48:17
 * @version 1.0
 */
@Component
public class CryptPasswordGeneratorUtils {

	/**
	 * Método responsável por criptografar uma String usando a hash SHA-256.
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
