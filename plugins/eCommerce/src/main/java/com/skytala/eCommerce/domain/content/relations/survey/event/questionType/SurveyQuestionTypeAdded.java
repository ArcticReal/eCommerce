package com.skytala.eCommerce.domain.content.relations.survey.event.questionType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionType.SurveyQuestionType;
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
