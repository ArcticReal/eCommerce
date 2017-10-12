package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;
public class BudgetItemAttributeDeleted implements Event{

	private boolean success;

	public BudgetItemAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
