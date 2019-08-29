package it.lucariccitelli.scheduledrestcaller.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;

import it.lucariccitelli.scheduledrestcaller.dao.entity.RestCall;
import it.lucariccitelli.scheduledrestcaller.dao.repository.RestCallRepository;

/**
 * @author luc created on 24 ago 2019
 *
 */
@Configuration
@EnableScheduling
public class RestCallScheduler {
	@Autowired
	private RestCallRepository callRepo;

	@Autowired
	private RestTemplate restCaller;

	/**
	 * Retrieve all call from database, process the call and delete entities older than
	 * six month
	 */
	@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
	public void processCall() {
		Date lastWeek = getLastWeekDate();
		Date lastSixMonth = getLastSixMonth();

		List<RestCall> restCallList = callRepo.findBySendStatusAndCreatedDateAfterOrderByCreatedDateAsc("N", lastWeek);

		for (RestCall call : restCallList) {
			ResponseEntity<String> response = callEndpoint(call);

			updateEntityResponse(call, response);

			callRepo.save(call);
		}

		callRepo.deleteByCreatedDateBefore(lastSixMonth);

	}

	/**
	 * Gets last week date
	 * 
	 * @return
	 */
	private Date getLastWeekDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	/**
	 * Gets last six month date
	 * 
	 * @return
	 */
	private Date getLastSixMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		return cal.getTime();
	}

	/**
	 * Calls a given endpoint
	 * 
	 * @param call
	 * @return
	 */
	private ResponseEntity<String> callEndpoint(RestCall call) {
		String url = prepareUrl(call);
		HttpEntity<String> request = prepareRequest(call);
		HttpMethod method = HttpMethod.resolve(call.getMethod());

		return restCaller.exchange(url, method, request, String.class);
	}

	/**
	 * Returns the URL to call with parameters
	 * 
	 * @param call
	 * @return
	 */
	private String prepareUrl(RestCall call) {
		StringBuilder url = new StringBuilder(call.getUrl());
		if (!Strings.isNullOrEmpty(call.getParam()))
			url.append(call.getParam());

		return url.toString();
	}

	private HttpEntity<String> prepareRequest(RestCall call) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		String body = (Strings.isNullOrEmpty(call.getBody())) ? null : call.getBody();

		return new HttpEntity<>(body, headers);
	}

	/**
	 * Set SendStatus to S (Send) if response is OK, otherwise E (error) if error
	 * set response body to feedback
	 * 
	 * @param call
	 * @param response
	 */
	private void updateEntityResponse(RestCall call, ResponseEntity<String> response) {
		String status = (response.getStatusCode().equals(HttpStatus.OK)) ? "S" : "E";

		call.setSendStatus(status);
		if (status.equals("E"))
			call.setFeedback(response.getBody());

	}

}
