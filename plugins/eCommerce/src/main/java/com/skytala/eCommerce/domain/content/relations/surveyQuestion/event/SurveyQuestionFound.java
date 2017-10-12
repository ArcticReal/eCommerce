package com.skytala.eCommerce.domain.content.relations.surveyQuestion.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestion.model.SurveyQuestion;
public class SurveyQuestionFound implements Event{

	private List<SurveyQuestion> surveyQuestions;

	public SurveyQuestionFound(List<SurveyQuestion> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}

	public List<SurveyQuestion> getSurveyQuestions()	{
		return surveyQuestions;
	}

}
