package com.skytala.eCommerce.domain.content.relations.surveyMultiResp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyMultiResp.model.SurveyMultiResp;
public class SurveyMultiRespUpdated implements Event{

	private boolean success;

	public SurveyMultiRespUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
