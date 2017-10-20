package com.skytala.eCommerce.domain.content.relations.survey.event.applType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.applType.SurveyApplType;
public class SurveyApplTypeUpdated implements Event{

	private boolean success;

	public SurveyApplTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
