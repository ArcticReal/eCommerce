package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;
public class BudgetItemAttributeUpdated implements Event{

	private boolean success;

	public BudgetItemAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
