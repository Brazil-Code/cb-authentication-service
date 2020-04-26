package br.com.brazilcode.cb.authentication.controller;

import java.lang.management.ManagementFactory;
import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brazilcode.cb.libs.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Class responsible for exposing an endpoint for {@link User} authentication.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:44:58 AM
 * @version 1.0
 */
@RestController
@RequestMapping("/")
@Api(value = "REST API for Authentication")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private final static long jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();

	/**
	 * Method responsible for validating JWT token.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/validateToken")
	@ApiOperation(value = "Validate the current session's token")
	public ResponseEntity<?> validateToken(Authentication auth) {
		return new ResponseEntity<>(auth.getPrincipal(), HttpStatus.OK);
	}

	/**
	 * Method responsible for returning JVM starting timestamp.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 */
	@GetMapping(path = "/serverStartTime")
	@ApiOperation(value = "Return the JVM start time in TIMESTAMP")
	public ResponseEntity<?> serverStartTime() {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(jvmStartTime);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return new ResponseEntity<>(calendar.getTimeInMillis(), HttpStatus.OK);
	}

}
