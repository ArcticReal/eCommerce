package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;
public class WorkEffortSkillStandardAdded implements Event{

	private WorkEffortSkillStandard addedWorkEffortSkillStandard;
	private boolean success;

	public WorkEffortSkillStandardAdded(WorkEffortSkillStandard addedWorkEffortSkillStandard, boolean success){
		this.addedWorkEffortSkillStandard = addedWorkEffortSkillStandard;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortSkillStandard getAddedWorkEffortSkillStandard() {
		return addedWorkEffortSkillStandard;
	}

}
