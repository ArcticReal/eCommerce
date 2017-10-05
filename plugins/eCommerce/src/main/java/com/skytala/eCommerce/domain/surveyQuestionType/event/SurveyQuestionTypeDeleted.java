package com.skytala.eCommerce.domain.surveyQuestionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyQuestionType.model.SurveyQuestionType;
public class SurveyQuestionTypeDeleted implements Event{

	private boolean success;

	public SurveyQuestionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
