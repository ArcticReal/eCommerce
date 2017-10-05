package com.skytala.eCommerce.domain.budgetType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetType.model.BudgetType;
public class BudgetTypeUpdated implements Event{

	private boolean success;

	public BudgetTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
