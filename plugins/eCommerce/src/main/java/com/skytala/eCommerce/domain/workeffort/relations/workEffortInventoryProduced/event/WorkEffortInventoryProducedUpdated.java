package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryProduced.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryProduced.model.WorkEffortInventoryProduced;
public class WorkEffortInventoryProducedUpdated implements Event{

	private boolean success;

	public WorkEffortInventoryProducedUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
