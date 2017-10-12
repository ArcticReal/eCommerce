package com.skytala.eCommerce.domain.accounting.relations.budget.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.Budget;
public class BudgetAdded implements Event{

	private Budget addedBudget;
	private boolean success;

	public BudgetAdded(Budget addedBudget, boolean success){
		this.addedBudget = addedBudget;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Budget getAddedBudget() {
		return addedBudget;
	}

}
