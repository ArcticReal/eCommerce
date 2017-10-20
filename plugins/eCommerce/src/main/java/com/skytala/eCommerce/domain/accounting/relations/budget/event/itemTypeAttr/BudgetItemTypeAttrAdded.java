package com.skytala.eCommerce.domain.accounting.relations.budget.event.itemTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemTypeAttr.BudgetItemTypeAttr;
public class BudgetItemTypeAttrAdded implements Event{

	private BudgetItemTypeAttr addedBudgetItemTypeAttr;
	private boolean success;

	public BudgetItemTypeAttrAdded(BudgetItemTypeAttr addedBudgetItemTypeAttr, boolean success){
		this.addedBudgetItemTypeAttr = addedBudgetItemTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetItemTypeAttr getAddedBudgetItemTypeAttr() {
		return addedBudgetItemTypeAttr;
	}

}
