package com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.model.SurveyMultiRespColumn;
public class SurveyMultiRespColumnUpdated implements Event{

	private boolean success;

	public SurveyMultiRespColumnUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
