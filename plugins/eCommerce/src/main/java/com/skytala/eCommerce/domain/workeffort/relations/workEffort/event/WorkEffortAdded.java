package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.WorkEffort;
public class WorkEffortAdded implements Event{

	private WorkEffort addedWorkEffort;
	private boolean success;

	public WorkEffortAdded(WorkEffort addedWorkEffort, boolean success){
		this.addedWorkEffort = addedWorkEffort;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffort getAddedWorkEffort() {
		return addedWorkEffort;
	}

}
