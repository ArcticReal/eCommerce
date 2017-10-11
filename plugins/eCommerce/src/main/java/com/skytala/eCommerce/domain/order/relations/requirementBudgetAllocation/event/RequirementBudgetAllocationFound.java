package com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.model.RequirementBudgetAllocation;
public class RequirementBudgetAllocationFound implements Event{

	private List<RequirementBudgetAllocation> requirementBudgetAllocations;

	public RequirementBudgetAllocationFound(List<RequirementBudgetAllocation> requirementBudgetAllocations) {
		this.requirementBudgetAllocations = requirementBudgetAllocations;
	}

	public List<RequirementBudgetAllocation> getRequirementBudgetAllocations()	{
		return requirementBudgetAllocations;
	}

}
