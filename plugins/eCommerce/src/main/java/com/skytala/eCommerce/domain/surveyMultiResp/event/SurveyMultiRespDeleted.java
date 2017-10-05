package com.skytala.eCommerce.domain.surveyMultiResp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyMultiResp.model.SurveyMultiResp;
public class SurveyMultiRespDeleted implements Event{

	private boolean success;

	public SurveyMultiRespDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
