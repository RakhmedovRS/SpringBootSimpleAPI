package com.github.rakhmedovrs.springboot.controller;

import com.github.rakhmedovrs.springboot.Application;
import com.github.rakhmedovrs.springboot.model.Question;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.util.Arrays;
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
	public void before()
	{
		httpHeaders.add("Authorization", createHttpAuthenticationHeader("user1", "password1"));
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	}

	private String createURL(String path)
	{
		return String.format("http://localhost:%s%s", port, path);
	}

	private String createHttpAuthenticationHeader(String userName, String password)
	{
		String auth = userName + ":" + password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.defaultCharset()));
		return "Basic " + new String(encodedAuth);
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

		HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<String> response = template.exchange(createURL("/survey/Survey1/questions/Question1"),
			HttpMethod.GET, entity, String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testCreateSurveyQuestion() throws Exception
	{
		Question question = new Question("99", "Test Question", "answer", Arrays.asList("1", "1", "1"));
		ResponseEntity<String> response = template.exchange(
			createURL("/survey/Survey1/questions/"),
			HttpMethod.POST,
			new HttpEntity<>(question, httpHeaders),
			String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		Assert.assertTrue(actual.contains("/survey/Survey1/questions/"));
	}
}
