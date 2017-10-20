package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.surveyAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.surveyAppl.WorkEffortSurveyAppl;
public class WorkEffortSurveyApplFound implements Event{

	private List<WorkEffortSurveyAppl> workEffortSurveyAppls;

	public WorkEffortSurveyApplFound(List<WorkEffortSurveyAppl> workEffortSurveyAppls) {
		this.workEffortSurveyAppls = workEffortSurveyAppls;
	}

	public List<WorkEffortSurveyAppl> getWorkEffortSurveyAppls()	{
		return workEffortSurveyAppls;
	}

}
