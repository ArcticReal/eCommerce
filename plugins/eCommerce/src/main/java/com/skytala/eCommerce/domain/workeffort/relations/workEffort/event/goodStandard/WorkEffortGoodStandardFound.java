package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandard;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandard.WorkEffortGoodStandard;
public class WorkEffortGoodStandardFound implements Event{

	private List<WorkEffortGoodStandard> workEffortGoodStandards;

	public WorkEffortGoodStandardFound(List<WorkEffortGoodStandard> workEffortGoodStandards) {
		this.workEffortGoodStandards = workEffortGoodStandards;
	}

	public List<WorkEffortGoodStandard> getWorkEffortGoodStandards()	{
		return workEffortGoodStandards;
	}

}
