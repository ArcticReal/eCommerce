package com.skytala.eCommerce.domain.accounting.relations.budget.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.type.BudgetType;
public class BudgetTypeDeleted implements Event{

	private boolean success;

	public BudgetTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
