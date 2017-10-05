package com.skytala.eCommerce.domain.surveyQuestion.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyQuestion.model.SurveyQuestion;
public class SurveyQuestionDeleted implements Event{

	private boolean success;

	public SurveyQuestionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
