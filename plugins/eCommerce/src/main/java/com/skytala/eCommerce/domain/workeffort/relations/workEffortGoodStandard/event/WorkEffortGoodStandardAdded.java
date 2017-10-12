package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.model.WorkEffortGoodStandard;
public class WorkEffortGoodStandardAdded implements Event{

	private WorkEffortGoodStandard addedWorkEffortGoodStandard;
	private boolean success;

	public WorkEffortGoodStandardAdded(WorkEffortGoodStandard addedWorkEffortGoodStandard, boolean success){
		this.addedWorkEffortGoodStandard = addedWorkEffortGoodStandard;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortGoodStandard getAddedWorkEffortGoodStandard() {
		return addedWorkEffortGoodStandard;
	}

}
