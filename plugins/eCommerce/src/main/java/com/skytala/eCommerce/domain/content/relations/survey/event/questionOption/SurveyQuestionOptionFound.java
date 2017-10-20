package com.skytala.eCommerce.domain.content.relations.survey.event.questionOption;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;
public class SurveyQuestionOptionFound implements Event{

	private List<SurveyQuestionOption> surveyQuestionOptions;

	public SurveyQuestionOptionFound(List<SurveyQuestionOption> surveyQuestionOptions) {
		this.surveyQuestionOptions = surveyQuestionOptions;
	}

	public List<SurveyQuestionOption> getSurveyQuestionOptions()	{
		return surveyQuestionOptions;
	}

}
