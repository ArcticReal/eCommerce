package com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.model.SurveyQuestionOption;
public class SurveyQuestionOptionFound implements Event{

	private List<SurveyQuestionOption> surveyQuestionOptions;

	public SurveyQuestionOptionFound(List<SurveyQuestionOption> surveyQuestionOptions) {
		this.surveyQuestionOptions = surveyQuestionOptions;
	}

	public List<SurveyQuestionOption> getSurveyQuestionOptions()	{
		return surveyQuestionOptions;
	}

}
