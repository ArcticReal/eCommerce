package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.type.WorkEffortType;
public class WorkEffortTypeAdded implements Event{

	private WorkEffortType addedWorkEffortType;
	private boolean success;

	public WorkEffortTypeAdded(WorkEffortType addedWorkEffortType, boolean success){
		this.addedWorkEffortType = addedWorkEffortType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortType getAddedWorkEffortType() {
		return addedWorkEffortType;
	}

}
