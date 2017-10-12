package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.model.WorkEffortGoodStandardType;
public class WorkEffortGoodStandardTypeAdded implements Event{

	private WorkEffortGoodStandardType addedWorkEffortGoodStandardType;
	private boolean success;

	public WorkEffortGoodStandardTypeAdded(WorkEffortGoodStandardType addedWorkEffortGoodStandardType, boolean success){
		this.addedWorkEffortGoodStandardType = addedWorkEffortGoodStandardType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortGoodStandardType getAddedWorkEffortGoodStandardType() {
		return addedWorkEffortGoodStandardType;
	}

}
