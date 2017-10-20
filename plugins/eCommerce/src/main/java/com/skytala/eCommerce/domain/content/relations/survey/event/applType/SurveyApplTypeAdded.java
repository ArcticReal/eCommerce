package com.skytala.eCommerce.domain.content.relations.survey.event.applType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.applType.SurveyApplType;
public class SurveyApplTypeAdded implements Event{

	private SurveyApplType addedSurveyApplType;
	private boolean success;

	public SurveyApplTypeAdded(SurveyApplType addedSurveyApplType, boolean success){
		this.addedSurveyApplType = addedSurveyApplType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyApplType getAddedSurveyApplType() {
		return addedSurveyApplType;
	}

}
