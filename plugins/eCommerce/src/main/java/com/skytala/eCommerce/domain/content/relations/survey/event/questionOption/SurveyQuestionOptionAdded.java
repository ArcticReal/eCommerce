package com.skytala.eCommerce.domain.content.relations.survey.event.questionOption;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;
public class SurveyQuestionOptionAdded implements Event{

	private SurveyQuestionOption addedSurveyQuestionOption;
	private boolean success;

	public SurveyQuestionOptionAdded(SurveyQuestionOption addedSurveyQuestionOption, boolean success){
		this.addedSurveyQuestionOption = addedSurveyQuestionOption;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyQuestionOption getAddedSurveyQuestionOption() {
		return addedSurveyQuestionOption;
	}

}
