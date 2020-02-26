package br.com.brazilcode.cb.authentication.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por agrupar e mapear as informações de ProfileDTO.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 25 de fev de 2020 13:38:02
 * @version 1.0
 */
public class ProfileDTO {

	private Long id;
	private String description;
	private List<FunctionalityDTO> functionalities = new ArrayList<>();

	public ProfileDTO() {
		super();
	}

	public ProfileDTO(Long id) {
		super();
		this.id = id;
	}

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

	public List<FunctionalityDTO> getFunctionalities() {
		return functionalities;
	}

	public void setFunctionalities(List<FunctionalityDTO> functionalities) {
		this.functionalities = functionalities;
	}

}
