package com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.model.SurveyQuestionOption;
public class SurveyQuestionOptionUpdated implements Event{

	private boolean success;

	public SurveyQuestionOptionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
