package com.skytala.eCommerce.domain.budgetItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetItemType.model.BudgetItemType;
public class BudgetItemTypeAdded implements Event{

	private BudgetItemType addedBudgetItemType;
	private boolean success;

	public BudgetItemTypeAdded(BudgetItemType addedBudgetItemType, boolean success){
		this.addedBudgetItemType = addedBudgetItemType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetItemType getAddedBudgetItemType() {
		return addedBudgetItemType;
	}

}
