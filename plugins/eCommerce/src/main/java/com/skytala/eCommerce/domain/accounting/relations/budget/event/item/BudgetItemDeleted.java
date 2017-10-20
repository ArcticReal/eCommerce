package com.skytala.eCommerce.domain.accounting.relations.budget.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.item.BudgetItem;
public class BudgetItemDeleted implements Event{

	private boolean success;

	public BudgetItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
