package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryProduced.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryProduced.model.WorkEffortInventoryProduced;
public class WorkEffortInventoryProducedDeleted implements Event{

	private boolean success;

	public WorkEffortInventoryProducedDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}