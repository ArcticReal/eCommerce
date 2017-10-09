package com.skytala.eCommerce.domain.survey.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.survey.model.Survey;
public class SurveyDeleted implements Event{

	private boolean success;

	public SurveyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}