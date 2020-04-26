package br.com.brazilcode.cb.authentication.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.brazilcode.cb.libs.model.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class responsible for grouping and mapping {@link Profile} information.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:35:06 AM
 * @version 2.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO {

	private Long id;
	private String description;
	private List<FunctionalityDTO> functionalities = new ArrayList<>();

	public ProfileDTO(Long id) {
		this();
		this.id = id;
	}

}
