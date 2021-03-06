package com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.typeAttr.BudgetTypeAttr;
public class BudgetTypeAttrAdded implements Event{

	private BudgetTypeAttr addedBudgetTypeAttr;
	private boolean success;

	public BudgetTypeAttrAdded(BudgetTypeAttr addedBudgetTypeAttr, boolean success){
		this.addedBudgetTypeAttr = addedBudgetTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetTypeAttr getAddedBudgetTypeAttr() {
		return addedBudgetTypeAttr;
	}

}
