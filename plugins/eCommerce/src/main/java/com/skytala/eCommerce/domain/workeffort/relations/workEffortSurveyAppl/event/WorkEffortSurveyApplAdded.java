package com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.model.WorkEffortSurveyAppl;
public class WorkEffortSurveyApplAdded implements Event{

	private WorkEffortSurveyAppl addedWorkEffortSurveyAppl;
	private boolean success;

	public WorkEffortSurveyApplAdded(WorkEffortSurveyAppl addedWorkEffortSurveyAppl, boolean success){
		this.addedWorkEffortSurveyAppl = addedWorkEffortSurveyAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortSurveyAppl getAddedWorkEffortSurveyAppl() {
		return addedWorkEffortSurveyAppl;
	}

}
