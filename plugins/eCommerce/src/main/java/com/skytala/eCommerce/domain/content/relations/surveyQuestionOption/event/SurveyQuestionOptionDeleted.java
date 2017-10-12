package com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.model.SurveyQuestionOption;
public class SurveyQuestionOptionDeleted implements Event{

	private boolean success;

	public SurveyQuestionOptionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
