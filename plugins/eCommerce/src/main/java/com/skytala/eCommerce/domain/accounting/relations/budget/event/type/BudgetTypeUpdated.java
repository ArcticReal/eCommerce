package com.skytala.eCommerce.domain.accounting.relations.budget.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.type.BudgetType;
public class BudgetTypeUpdated implements Event{

	private boolean success;

	public BudgetTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
