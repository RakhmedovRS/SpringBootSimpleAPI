package com.github.rakhmedovrs.springboot.controller;

import com.github.rakhmedovrs.springboot.model.Question;
import com.github.rakhmedovrs.springboot.service.SurveyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

/**
 * @author RakhmedovRS
 * @created 02-Apr-20
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SurveyController.class)
public class SurveyControllerTest
{
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private SurveyService surveyService;

	@Test
	@WithMockUser(username = "user1", password = "password1")
	public void test() throws Exception
	{
		Question mockQuestion = new Question("Question1",
			"Largest Country in the World", "Russia", Arrays.asList(
			"India", "Russia", "United States", "China"));

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

		Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(mockQuestion);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
			.get("/survey/Survey1/questions/Question1")
			.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	@WithMockUser(username = "user1", password = "password1")
	public void createSurveyQuestion() throws Exception
	{
		Question mockQuestion = new Question("Question1",
			"Largest Country in the World", "Russia", Arrays.asList(
			"India", "Russia", "United States", "China"));

		String questionJSON = "{\n" +
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

		Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(mockQuestion);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/survey/Survey1/questions")
			.accept(MediaType.APPLICATION_JSON).content(questionJSON)
			.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Assert.assertTrue(response.getHeaders(HttpHeaders.LOCATION).get(0).contains("/survey/Survey1/questions/Question1"));
	}
}
