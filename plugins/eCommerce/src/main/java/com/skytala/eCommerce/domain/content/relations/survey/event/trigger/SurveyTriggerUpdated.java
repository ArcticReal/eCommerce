package com.skytala.eCommerce.domain.content.relations.survey.event.trigger;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.trigger.SurveyTrigger;
public class SurveyTriggerUpdated implements Event{

	private boolean success;

	public SurveyTriggerUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
