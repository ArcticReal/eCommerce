package com.skytala.eCommerce.domain.content.relations.survey.event.applType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.applType.SurveyApplType;
public class SurveyApplTypeFound implements Event{

	private List<SurveyApplType> surveyApplTypes;

	public SurveyApplTypeFound(List<SurveyApplType> surveyApplTypes) {
		this.surveyApplTypes = surveyApplTypes;
	}

	public List<SurveyApplType> getSurveyApplTypes()	{
		return surveyApplTypes;
	}

}
