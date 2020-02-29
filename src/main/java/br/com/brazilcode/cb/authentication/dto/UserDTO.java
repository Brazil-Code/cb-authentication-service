package br.com.brazilcode.cb.authentication.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por agrupar e mapear as informações do UserDTO.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 13:33:52
 * @version 1.0
 */
public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String token;
	private List<ProfileDTO> profiles = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<ProfileDTO> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileDTO> profiles) {
		this.profiles = profiles;
	}

}
