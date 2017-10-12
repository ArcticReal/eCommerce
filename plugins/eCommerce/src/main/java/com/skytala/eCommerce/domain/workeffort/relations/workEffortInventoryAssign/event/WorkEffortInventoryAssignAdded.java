package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.model.WorkEffortInventoryAssign;
public class WorkEffortInventoryAssignAdded implements Event{

	private WorkEffortInventoryAssign addedWorkEffortInventoryAssign;
	private boolean success;

	public WorkEffortInventoryAssignAdded(WorkEffortInventoryAssign addedWorkEffortInventoryAssign, boolean success){
		this.addedWorkEffortInventoryAssign = addedWorkEffortInventoryAssign;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortInventoryAssign getAddedWorkEffortInventoryAssign() {
		return addedWorkEffortInventoryAssign;
	}

}
