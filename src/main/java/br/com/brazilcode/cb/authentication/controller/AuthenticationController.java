package br.com.brazilcode.cb.authentication.controller;

import java.lang.management.ManagementFactory;
import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthenticationController {

	private final static long jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();

	/**
	 * Método responsável por fazer a validação do token JWT.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/validateToken")
	public ResponseEntity<?> validateToken(Authentication auth) {
		return new ResponseEntity<>(auth.getPrincipal(), HttpStatus.OK);
	}

	/**
	 * Método responsável por retornar a data e hora de inicialização da JVM para
	 * este server.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 */
	@GetMapping(path = "/serverStartTime")
	public ResponseEntity<?> serverStartTime() {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(jvmStartTime);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return new ResponseEntity<>(calendar.getTimeInMillis(), HttpStatus.OK);
	}

}