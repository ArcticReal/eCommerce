package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;
public class WorkEffortInventoryProducedDeleted implements Event{

	private boolean success;

	public WorkEffortInventoryProducedDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
