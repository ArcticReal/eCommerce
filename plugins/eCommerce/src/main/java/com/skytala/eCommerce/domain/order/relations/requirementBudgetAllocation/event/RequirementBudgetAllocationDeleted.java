package com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.model.RequirementBudgetAllocation;
public class RequirementBudgetAllocationDeleted implements Event{

	private boolean success;

	public RequirementBudgetAllocationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
