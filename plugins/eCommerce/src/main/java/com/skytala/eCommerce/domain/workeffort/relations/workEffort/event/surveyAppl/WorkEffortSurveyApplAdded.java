package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.surveyAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.surveyAppl.WorkEffortSurveyAppl;
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
