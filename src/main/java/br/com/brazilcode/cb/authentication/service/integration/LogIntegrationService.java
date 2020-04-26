package br.com.brazilcode.cb.authentication.service.integration;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.brazilcode.cb.authentication.constants.LoginConstants;
import br.com.brazilcode.cb.authentication.exception.integration.LogIntegrationServiceException;
import br.com.brazilcode.cb.libs.dto.LogDTO;

/**
 * Class responsible for integrating with the Log Service of the Administration module.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since Apr 26, 2020 1:57:47 AM
 * @version 1.0
 */
@Service
public class LogIntegrationService implements Serializable {

	private static final long serialVersionUID = 1390649214746113731L;

	private static final Logger LOGGER = LoggerFactory.getLogger(LogIntegrationService.class);

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
		LOGGER.info(method + "BEGIN");

		RestTemplate restTemplate = new RestTemplate();
		final String url = administrationServiceURL + "logs";

		LOGGER.info(method + "User ID: " + userId);
		LogDTO logDTO = new LogDTO(userId, description);

		LOGGER.info(method + "Setting HTTP Headers");
		HttpHeaders headers = new HttpHeaders();
		headers.set(LoginConstants.HEADER_STRING, authorization);

		LOGGER.info(method + "Building request");
		HttpEntity<LogDTO> request = new HttpEntity<>(logDTO, headers);

		try {
			LOGGER.info(method + "Calling the following URL: " + url);
			restTemplate.postForEntity(url, request, null);
		} catch (Exception e) {
			LOGGER.error(method + e.getMessage(), e);
			throw new LogIntegrationServiceException(e.getMessage(), e);
		} finally {
			LOGGER.info(method + "END");
		}
	}

}
