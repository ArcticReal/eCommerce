package com.skytala.eCommerce.domain.content.relations.surveyQuestionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionType.model.SurveyQuestionType;
public class SurveyQuestionTypeAdded implements Event{

	private SurveyQuestionType addedSurveyQuestionType;
	private boolean success;

	public SurveyQuestionTypeAdded(SurveyQuestionType addedSurveyQuestionType, boolean success){
		this.addedSurveyQuestionType = addedSurveyQuestionType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyQuestionType getAddedSurveyQuestionType() {
		return addedSurveyQuestionType;
	}

}
