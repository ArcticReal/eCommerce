package com.skytala.eCommerce.domain.surveyResponse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyResponse.model.SurveyResponse;
public class SurveyResponseDeleted implements Event{

	private boolean success;

	public SurveyResponseDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
