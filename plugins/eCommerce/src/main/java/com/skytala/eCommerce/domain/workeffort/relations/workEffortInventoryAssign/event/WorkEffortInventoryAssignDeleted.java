package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.model.WorkEffortInventoryAssign;
public class WorkEffortInventoryAssignDeleted implements Event{

	private boolean success;

	public WorkEffortInventoryAssignDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}