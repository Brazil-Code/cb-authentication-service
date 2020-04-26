package br.com.brazilcode.cb.authentication.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.brazilcode.cb.libs.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Class responsible for grouping and mapping {@link User} information.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:34:09 AM
 * @version 2.0
 */
@Getter
@Setter
public class UserDTO {

	private Long id;
	private AreaDTO area;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String token;
	private List<ProfileDTO> profiles = new ArrayList<>();

}
