package com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.model.WorkEffortSurveyAppl;
public class WorkEffortSurveyApplFound implements Event{

	private List<WorkEffortSurveyAppl> workEffortSurveyAppls;

	public WorkEffortSurveyApplFound(List<WorkEffortSurveyAppl> workEffortSurveyAppls) {
		this.workEffortSurveyAppls = workEffortSurveyAppls;
	}

	public List<WorkEffortSurveyAppl> getWorkEffortSurveyAppls()	{
		return workEffortSurveyAppls;
	}

}
