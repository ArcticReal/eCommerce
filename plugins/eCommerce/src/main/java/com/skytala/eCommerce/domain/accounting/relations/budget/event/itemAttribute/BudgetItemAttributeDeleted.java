package com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemAttribute.BudgetItemAttribute;
public class BudgetItemAttributeDeleted implements Event{

	private boolean success;

	public BudgetItemAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
