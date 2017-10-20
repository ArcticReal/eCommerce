package com.skytala.eCommerce.domain.content.relations.survey.event.questionType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionType.SurveyQuestionType;
public class SurveyQuestionTypeFound implements Event{

	private List<SurveyQuestionType> surveyQuestionTypes;

	public SurveyQuestionTypeFound(List<SurveyQuestionType> surveyQuestionTypes) {
		this.surveyQuestionTypes = surveyQuestionTypes;
	}

	public List<SurveyQuestionType> getSurveyQuestionTypes()	{
		return surveyQuestionTypes;
	}

}
