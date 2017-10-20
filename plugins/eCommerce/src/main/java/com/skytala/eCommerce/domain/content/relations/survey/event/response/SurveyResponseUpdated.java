package com.skytala.eCommerce.domain.content.relations.survey.event.response;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.response.SurveyResponse;
public class SurveyResponseUpdated implements Event{

	private boolean success;

	public SurveyResponseUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
