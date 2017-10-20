package com.skytala.eCommerce.domain.content.relations.survey.event.response;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.response.SurveyResponse;
public class SurveyResponseAdded implements Event{

	private SurveyResponse addedSurveyResponse;
	private boolean success;

	public SurveyResponseAdded(SurveyResponse addedSurveyResponse, boolean success){
		this.addedSurveyResponse = addedSurveyResponse;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyResponse getAddedSurveyResponse() {
		return addedSurveyResponse;
	}

}
