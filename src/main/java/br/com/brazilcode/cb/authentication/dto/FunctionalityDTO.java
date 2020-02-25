package br.com.brazilcode.cb.authentication.dto;

/**
 * Classe responsável por agrupar e mapear as informações do FunctionalityDTO.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 13:58:20
 * @version 1.0
 */
public class FunctionalityDTO {

	private Long id;
	private String description;
	private boolean disabled;
	private String uri;
	private Integer action;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

}
