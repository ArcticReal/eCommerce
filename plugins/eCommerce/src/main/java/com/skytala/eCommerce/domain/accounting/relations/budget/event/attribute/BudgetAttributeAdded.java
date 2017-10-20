package com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute.BudgetAttribute;
public class BudgetAttributeAdded implements Event{

	private BudgetAttribute addedBudgetAttribute;
	private boolean success;

	public BudgetAttributeAdded(BudgetAttribute addedBudgetAttribute, boolean success){
		this.addedBudgetAttribute = addedBudgetAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetAttribute getAddedBudgetAttribute() {
		return addedBudgetAttribute;
	}

}
