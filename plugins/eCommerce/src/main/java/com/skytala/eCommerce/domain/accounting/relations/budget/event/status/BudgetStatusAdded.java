package com.skytala.eCommerce.domain.accounting.relations.budget.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.status.BudgetStatus;
public class BudgetStatusAdded implements Event{

	private BudgetStatus addedBudgetStatus;
	private boolean success;

	public BudgetStatusAdded(BudgetStatus addedBudgetStatus, boolean success){
		this.addedBudgetStatus = addedBudgetStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetStatus getAddedBudgetStatus() {
		return addedBudgetStatus;
	}

}
