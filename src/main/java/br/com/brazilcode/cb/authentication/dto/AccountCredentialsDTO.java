package br.com.brazilcode.cb.authentication.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Class responsible for grouping and mapping the user's access credentials.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:29:55 AM
 * @version 2.0
 */
@Getter
@Setter
public class AccountCredentialsDTO {

	private String username;
	private String password;

}
