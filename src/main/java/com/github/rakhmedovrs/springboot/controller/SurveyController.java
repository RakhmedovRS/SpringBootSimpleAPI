package com.github.rakhmedovrs.springboot.controller;

import com.github.rakhmedovrs.springboot.model.Question;
import com.github.rakhmedovrs.springboot.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
