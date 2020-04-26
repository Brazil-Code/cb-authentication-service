package br.com.brazilcode.cb.authentication.dto;

import br.com.brazilcode.cb.libs.model.Functionality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class responsible for grouping and mapping {@link Functionality} information.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:34:39 AM
 * @version 2.0
 */
@Getter
@Setter
@NoArgsConstructor
public class FunctionalityDTO {

	private Long id;
	private String description;
	private boolean disabled = false;
	private String uri;
	private Integer action;

	public FunctionalityDTO(Long id) {
		this();
		this.id = id;
	}

}
