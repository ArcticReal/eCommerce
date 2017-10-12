package com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.model.SurveyMultiRespColumn;
public class SurveyMultiRespColumnDeleted implements Event{

	private boolean success;

	public SurveyMultiRespColumnDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
