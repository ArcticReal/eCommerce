package com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.model.WorkEffortSurveyAppl;
public class WorkEffortSurveyApplDeleted implements Event{

	private boolean success;

	public WorkEffortSurveyApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
