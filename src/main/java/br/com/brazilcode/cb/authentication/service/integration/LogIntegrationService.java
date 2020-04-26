package br.com.brazilcode.cb.authentication.service.integration;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.brazilcode.cb.authentication.constants.LoginConstants;
import br.com.brazilcode.cb.authentication.exception.integration.LogIntegrationServiceException;
import br.com.brazilcode.cb.libs.dto.LogDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * Class responsible for integrating with the Log Service of the Administration module.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:57:47 AM
 * @version 1.0
 */
@Service
@Slf4j
public class LogIntegrationService implements Serializable {

	private static final long serialVersionUID = 1390649214746113731L;

	@Value("${cb.administration.service.url}")
	private String administrationServiceURL;

	/**
	 * Async method responsible for integrating with the LOGs creation API via POST request.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param userId
	 * @param description
	 * @param authorization
	 * @throws LogIntegrationServiceException
	 */
	@Async
	public void createLog(Long userId, String description, String authorization) throws LogIntegrationServiceException {
		final String method = "[ LogIntegrationService ] - ";
		log.info(method + "BEGIN");

		RestTemplate restTemplate = new RestTemplate();
		final String url = administrationServiceURL + "logs";

		log.info(method + "User ID: " + userId);
		LogDTO logDTO = new LogDTO(userId, description);

		log.info(method + "Setting HTTP Headers");
		HttpHeaders headers = new HttpHeaders();
		headers.set(LoginConstants.HEADER_STRING, authorization);

		log.info(method + "Building request");
		HttpEntity<LogDTO> request = new HttpEntity<>(logDTO, headers);

		try {
			log.info(method + "Calling the following URL: " + url);
			restTemplate.postForEntity(url, request, null);
		} catch (Exception e) {
			log.error(method + e.getMessage(), e);
			throw new LogIntegrationServiceException(e.getMessage(), e);
		} finally {
			log.info(method + "END");
		}
	}

}
