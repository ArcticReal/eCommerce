package com.skytala.eCommerce.domain.content.relations.surveyMultiResp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyMultiResp.model.SurveyMultiResp;
public class SurveyMultiRespAdded implements Event{

	private SurveyMultiResp addedSurveyMultiResp;
	private boolean success;

	public SurveyMultiRespAdded(SurveyMultiResp addedSurveyMultiResp, boolean success){
		this.addedSurveyMultiResp = addedSurveyMultiResp;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyMultiResp getAddedSurveyMultiResp() {
		return addedSurveyMultiResp;
	}

}
