package com.skytala.eCommerce.domain.content.relations.surveyTrigger.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyTrigger.model.SurveyTrigger;
public class SurveyTriggerUpdated implements Event{

	private boolean success;

	public SurveyTriggerUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
