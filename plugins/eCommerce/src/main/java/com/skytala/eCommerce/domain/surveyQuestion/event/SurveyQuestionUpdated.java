package com.skytala.eCommerce.domain.surveyQuestion.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyQuestion.model.SurveyQuestion;
public class SurveyQuestionUpdated implements Event{

	private boolean success;

	public SurveyQuestionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
