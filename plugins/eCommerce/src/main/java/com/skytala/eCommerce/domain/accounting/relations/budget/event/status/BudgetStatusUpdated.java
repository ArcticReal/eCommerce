package com.skytala.eCommerce.domain.accounting.relations.budget.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.status.BudgetStatus;
public class BudgetStatusUpdated implements Event{

	private boolean success;

	public BudgetStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
