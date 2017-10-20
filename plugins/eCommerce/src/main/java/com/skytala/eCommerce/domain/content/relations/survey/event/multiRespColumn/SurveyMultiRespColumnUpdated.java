package com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;
public class SurveyMultiRespColumnUpdated implements Event{

	private boolean success;

	public SurveyMultiRespColumnUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
