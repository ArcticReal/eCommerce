package com.skytala.eCommerce.domain.content.relations.surveyTrigger.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyTrigger.model.SurveyTrigger;
public class SurveyTriggerDeleted implements Event{

	private boolean success;

	public SurveyTriggerDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
