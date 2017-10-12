package com.skytala.eCommerce.domain.accounting.relations.budgetType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetType.model.BudgetType;
public class BudgetTypeDeleted implements Event{

	private boolean success;

	public BudgetTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
