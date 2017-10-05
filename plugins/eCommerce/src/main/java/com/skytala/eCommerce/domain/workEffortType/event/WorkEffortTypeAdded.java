package com.skytala.eCommerce.domain.workEffortType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortType.model.WorkEffortType;
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
