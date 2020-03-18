package com.github.rakhmedovrs.springboot.controller;

import com.github.rakhmedovrs.springboot.model.Question;
import com.github.rakhmedovrs.springboot.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

/**
 * @author RakhmedovRS
 * @created 16-Mar-20
 */
@RestController
public class SurveyController
{
	@Autowired
	private SurveyService surveyService;

	@RequestMapping(path = "/survey/{surveyId}/questions", method = RequestMethod.GET)
	public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId)
	{
		return surveyService.retrieveQuestions(surveyId);
	}

	@RequestMapping(path = "/survey/{surveyId}/questions/{questionID}", method = RequestMethod.GET)
	public Question retrieveQuestionForSurvey(@PathVariable String surveyId,
	                                           @PathVariable String questionID)
	{
		return surveyService.retrieveQuestion(surveyId, questionID);
	}

	@RequestMapping(path = "/survey/{surveyId}/questions", method = RequestMethod.POST)
	public ResponseEntity<?> addQuestionToSurvey(@PathVariable String surveyId,
	                                                  @Valid @RequestBody Question newQuestion)
	{
		Question question = surveyService.addQuestion(surveyId, newQuestion);
		if (question == null)
		{
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(question.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}
}
