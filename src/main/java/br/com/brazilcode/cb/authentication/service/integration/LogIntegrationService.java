package br.com.brazilcode.cb.authentication.service.integration;

import java.io.Serializable;
import java.util.Calendar;

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
 * Classe responsável por fazer a integração via REST com o serviço de Logs do
 * módulo Administration.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 26 de mar de 2020 23:06:06
 * @version 1.0
 */
@Service
public class LogIntegrationService implements Serializable {

	private static final long serialVersionUID = 1390649214746113731L;

	private static final Logger LOGGER = LoggerFactory.getLogger(LogIntegrationService.class);

	@Value("${cb.administration.service.url}")
	private String administrationServiceURL;

	/**
	 * Método assíncrono responsável por fazer uma chamada via REST para o serviço
	 * de gravação de Logs.
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
		LOGGER.debug(method + "BEGIN");

		RestTemplate restTemplate = new RestTemplate();
		final String url = administrationServiceURL + "logs";

		LOGGER.debug(method + "User ID: " + userId);
		LogDTO logDTO = new LogDTO(userId, description, String.valueOf(Calendar.getInstance()));

		LOGGER.debug(method + "Setting HTTP Headers");
		HttpHeaders headers = new HttpHeaders();
		headers.set(LoginConstants.HEADER_STRING, authorization);

		LOGGER.debug(method + "Building request");
		HttpEntity<LogDTO> request = new HttpEntity<>(logDTO, headers);

		try {
			LOGGER.debug(method + "Calling the following URL: " + url);
			restTemplate.postForEntity(url, request, null);
		} catch (Exception e) {
			LOGGER.error(method + e.getMessage(), e);
			throw new LogIntegrationServiceException(e.getMessage(), e);
		} finally {
			LOGGER.debug(method + "END");
		}
	}

}
