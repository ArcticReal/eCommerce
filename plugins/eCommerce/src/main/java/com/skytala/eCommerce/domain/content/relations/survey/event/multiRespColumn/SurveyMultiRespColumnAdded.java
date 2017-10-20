package com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;
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
