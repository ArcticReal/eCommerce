package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryAssign;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryAssign.WorkEffortInventoryAssign;
public class WorkEffortInventoryAssignDeleted implements Event{

	private boolean success;

	public WorkEffortInventoryAssignDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
