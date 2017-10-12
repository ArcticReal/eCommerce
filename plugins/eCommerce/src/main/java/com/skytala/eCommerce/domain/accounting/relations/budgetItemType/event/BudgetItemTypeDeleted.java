package com.skytala.eCommerce.domain.accounting.relations.budgetItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemType.model.BudgetItemType;
public class BudgetItemTypeDeleted implements Event{

	private boolean success;

	public BudgetItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
