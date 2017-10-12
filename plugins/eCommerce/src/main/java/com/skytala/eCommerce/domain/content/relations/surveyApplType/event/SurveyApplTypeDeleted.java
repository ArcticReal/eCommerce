package com.skytala.eCommerce.domain.content.relations.surveyApplType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyApplType.model.SurveyApplType;
public class SurveyApplTypeDeleted implements Event{

	private boolean success;

	public SurveyApplTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
