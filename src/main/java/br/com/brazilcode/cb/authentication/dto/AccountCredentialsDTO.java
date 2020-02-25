package br.com.brazilcode.cb.authentication.dto;

/**
 * Classe responsável por agrupar e mapear as credenciais de acesso do usuário.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 15:32:28
 * @version 1.0
 */
public class AccountCredentialsDTO {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
