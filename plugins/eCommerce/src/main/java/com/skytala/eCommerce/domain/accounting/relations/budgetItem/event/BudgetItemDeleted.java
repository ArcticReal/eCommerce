package com.skytala.eCommerce.domain.accounting.relations.budgetItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItem.model.BudgetItem;
public class BudgetItemDeleted implements Event{

	private boolean success;

	public BudgetItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
