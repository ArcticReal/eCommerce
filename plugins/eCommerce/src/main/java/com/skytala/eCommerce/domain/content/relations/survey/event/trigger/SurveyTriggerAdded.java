package com.skytala.eCommerce.domain.content.relations.survey.event.trigger;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.trigger.SurveyTrigger;
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
