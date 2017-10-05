package com.skytala.eCommerce.domain.budget.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budget.model.Budget;
public class BudgetDeleted implements Event{

	private boolean success;

	public BudgetDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
