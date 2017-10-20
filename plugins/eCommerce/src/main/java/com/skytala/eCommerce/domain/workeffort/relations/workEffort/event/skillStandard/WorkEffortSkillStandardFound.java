package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;
public class WorkEffortSkillStandardFound implements Event{

	private List<WorkEffortSkillStandard> workEffortSkillStandards;

	public WorkEffortSkillStandardFound(List<WorkEffortSkillStandard> workEffortSkillStandards) {
		this.workEffortSkillStandards = workEffortSkillStandards;
	}

	public List<WorkEffortSkillStandard> getWorkEffortSkillStandards()	{
		return workEffortSkillStandards;
	}

}
