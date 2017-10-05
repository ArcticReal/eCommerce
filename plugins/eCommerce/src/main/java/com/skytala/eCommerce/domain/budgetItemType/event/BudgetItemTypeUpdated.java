package com.skytala.eCommerce.domain.budgetItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetItemType.model.BudgetItemType;
public class BudgetItemTypeUpdated implements Event{

	private boolean success;

	public BudgetItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
