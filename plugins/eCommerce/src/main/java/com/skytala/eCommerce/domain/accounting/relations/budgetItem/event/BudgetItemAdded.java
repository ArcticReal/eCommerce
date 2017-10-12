package com.skytala.eCommerce.domain.accounting.relations.budgetItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItem.model.BudgetItem;
public class BudgetItemAdded implements Event{

	private BudgetItem addedBudgetItem;
	private boolean success;

	public BudgetItemAdded(BudgetItem addedBudgetItem, boolean success){
		this.addedBudgetItem = addedBudgetItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetItem getAddedBudgetItem() {
		return addedBudgetItem;
	}

}
