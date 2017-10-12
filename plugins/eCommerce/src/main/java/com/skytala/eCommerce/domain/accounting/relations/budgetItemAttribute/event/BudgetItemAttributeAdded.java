package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;
public class BudgetItemAttributeAdded implements Event{

	private BudgetItemAttribute addedBudgetItemAttribute;
	private boolean success;

	public BudgetItemAttributeAdded(BudgetItemAttribute addedBudgetItemAttribute, boolean success){
		this.addedBudgetItemAttribute = addedBudgetItemAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetItemAttribute getAddedBudgetItemAttribute() {
		return addedBudgetItemAttribute;
	}

}
