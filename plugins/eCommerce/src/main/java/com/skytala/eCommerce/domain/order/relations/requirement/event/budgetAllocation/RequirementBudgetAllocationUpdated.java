package com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.budgetAllocation.RequirementBudgetAllocation;
public class RequirementBudgetAllocationUpdated implements Event{

	private boolean success;

	public RequirementBudgetAllocationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
