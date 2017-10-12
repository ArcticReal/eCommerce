package com.skytala.eCommerce.domain.content.relations.survey.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.Survey;
public class SurveyAdded implements Event{

	private Survey addedSurvey;
	private boolean success;

	public SurveyAdded(Survey addedSurvey, boolean success){
		this.addedSurvey = addedSurvey;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Survey getAddedSurvey() {
		return addedSurvey;
	}

}
