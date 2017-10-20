package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryAssign;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryAssign.WorkEffortInventoryAssign;
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
