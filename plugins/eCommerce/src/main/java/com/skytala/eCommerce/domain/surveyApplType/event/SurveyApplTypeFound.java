package com.skytala.eCommerce.domain.surveyApplType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyApplType.model.SurveyApplType;
public class SurveyApplTypeFound implements Event{

	private List<SurveyApplType> surveyApplTypes;

	public SurveyApplTypeFound(List<SurveyApplType> surveyApplTypes) {
		this.surveyApplTypes = surveyApplTypes;
	}

	public List<SurveyApplType> getSurveyApplTypes()	{
		return surveyApplTypes;
	}

}
