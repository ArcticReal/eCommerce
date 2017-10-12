package com.skytala.eCommerce.domain.content.relations.surveyResponse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyResponse.model.SurveyResponse;
public class SurveyResponseUpdated implements Event{

	private boolean success;

	public SurveyResponseUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
