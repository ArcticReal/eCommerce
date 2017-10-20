package com.skytala.eCommerce.domain.content.relations.survey.event.multiResp;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.multiResp.SurveyMultiResp;
public class SurveyMultiRespUpdated implements Event{

	private boolean success;

	public SurveyMultiRespUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
