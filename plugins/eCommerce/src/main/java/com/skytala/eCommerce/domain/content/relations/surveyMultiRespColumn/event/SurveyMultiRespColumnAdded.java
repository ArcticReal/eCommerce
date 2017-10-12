package com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.model.SurveyMultiRespColumn;
public class SurveyMultiRespColumnAdded implements Event{

	private SurveyMultiRespColumn addedSurveyMultiRespColumn;
	private boolean success;

	public SurveyMultiRespColumnAdded(SurveyMultiRespColumn addedSurveyMultiRespColumn, boolean success){
		this.addedSurveyMultiRespColumn = addedSurveyMultiRespColumn;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyMultiRespColumn getAddedSurveyMultiRespColumn() {
		return addedSurveyMultiRespColumn;
	}

}
