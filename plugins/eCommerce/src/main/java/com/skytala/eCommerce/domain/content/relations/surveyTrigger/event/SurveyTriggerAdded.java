package com.skytala.eCommerce.domain.content.relations.surveyTrigger.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyTrigger.model.SurveyTrigger;
public class SurveyTriggerAdded implements Event{

	private SurveyTrigger addedSurveyTrigger;
	private boolean success;

	public SurveyTriggerAdded(SurveyTrigger addedSurveyTrigger, boolean success){
		this.addedSurveyTrigger = addedSurveyTrigger;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyTrigger getAddedSurveyTrigger() {
		return addedSurveyTrigger;
	}

}
