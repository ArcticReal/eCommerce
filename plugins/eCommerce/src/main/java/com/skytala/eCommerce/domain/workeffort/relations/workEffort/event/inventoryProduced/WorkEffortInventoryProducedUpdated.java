package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;
public class WorkEffortInventoryProducedUpdated implements Event{

	private boolean success;

	public WorkEffortInventoryProducedUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
