package com.skytala.eCommerce.domain.accounting.relations.budget.event.itemType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemType.BudgetItemType;
public class BudgetItemTypeUpdated implements Event{

	private boolean success;

	public BudgetItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
