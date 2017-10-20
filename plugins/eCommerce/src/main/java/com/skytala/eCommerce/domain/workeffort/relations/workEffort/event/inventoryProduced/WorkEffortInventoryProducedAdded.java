package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;
public class WorkEffortInventoryProducedAdded implements Event{

	private WorkEffortInventoryProduced addedWorkEffortInventoryProduced;
	private boolean success;

	public WorkEffortInventoryProducedAdded(WorkEffortInventoryProduced addedWorkEffortInventoryProduced, boolean success){
		this.addedWorkEffortInventoryProduced = addedWorkEffortInventoryProduced;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortInventoryProduced getAddedWorkEffortInventoryProduced() {
		return addedWorkEffortInventoryProduced;
	}

}
