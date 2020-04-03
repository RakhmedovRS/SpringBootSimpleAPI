package com.github.rakhmedovrs.springboot.controller;

import com.github.rakhmedovrs.springboot.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author RakhmedovRS
 * @created 02-Apr-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT
{
	@LocalServerPort
	private int port;

	private TestRestTemplate template = new TestRestTemplate();
	private HttpHeaders httpHeaders = new HttpHeaders();

	@Before
	public void setupJSONAcceptType()
	{
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testRetrieveSurveyQuestion() throws Exception
	{
		String expected = "{\n" +
			"    \"id\": \"Question1\",\n" +
			"    \"description\": \"Largest Country in the World\",\n" +
			"    \"correctAnswer\": \"Russia\",\n" +
			"    \"options\": [\n" +
			"        \"India\",\n" +
			"        \"Russia\",\n" +
			"        \"United States\",\n" +
			"        \"China\"\n" +
			"    ]\n" +
			"}";

		String url = String.format("http://localhost:%s/survey/Survey1/questions/Question1", port);
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
}
