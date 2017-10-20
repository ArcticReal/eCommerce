package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.surveyAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.surveyAppl.WorkEffortSurveyAppl;
public class WorkEffortSurveyApplUpdated implements Event{

	private boolean success;

	public WorkEffortSurveyApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
