package com.skytala.eCommerce.domain.content.relations.survey.event.trigger;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.trigger.SurveyTrigger;
public class SurveyTriggerDeleted implements Event{

	private boolean success;

	public SurveyTriggerDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
