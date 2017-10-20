package com.skytala.eCommerce.domain.accounting.relations.budget.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.type.BudgetType;
public class BudgetTypeAdded implements Event{

	private BudgetType addedBudgetType;
	private boolean success;

	public BudgetTypeAdded(BudgetType addedBudgetType, boolean success){
		this.addedBudgetType = addedBudgetType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetType getAddedBudgetType() {
		return addedBudgetType;
	}

}
