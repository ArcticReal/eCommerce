package com.skytala.eCommerce.domain.budget.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budget.model.Budget;
public class BudgetUpdated implements Event{

	private boolean success;

	public BudgetUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
