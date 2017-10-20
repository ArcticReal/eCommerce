package com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.budgetAllocation.RequirementBudgetAllocation;
public class RequirementBudgetAllocationAdded implements Event{

	private RequirementBudgetAllocation addedRequirementBudgetAllocation;
	private boolean success;

	public RequirementBudgetAllocationAdded(RequirementBudgetAllocation addedRequirementBudgetAllocation, boolean success){
		this.addedRequirementBudgetAllocation = addedRequirementBudgetAllocation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RequirementBudgetAllocation getAddedRequirementBudgetAllocation() {
		return addedRequirementBudgetAllocation;
	}

}
