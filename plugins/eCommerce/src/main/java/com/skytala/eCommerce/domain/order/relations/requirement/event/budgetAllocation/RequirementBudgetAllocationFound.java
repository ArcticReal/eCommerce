package com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.budgetAllocation.RequirementBudgetAllocation;
public class RequirementBudgetAllocationFound implements Event{

	private List<RequirementBudgetAllocation> requirementBudgetAllocations;

	public RequirementBudgetAllocationFound(List<RequirementBudgetAllocation> requirementBudgetAllocations) {
		this.requirementBudgetAllocations = requirementBudgetAllocations;
	}

	public List<RequirementBudgetAllocation> getRequirementBudgetAllocations()	{
		return requirementBudgetAllocations;
	}

}
