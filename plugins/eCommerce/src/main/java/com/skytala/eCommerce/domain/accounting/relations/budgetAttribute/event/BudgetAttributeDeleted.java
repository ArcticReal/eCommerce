package com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.model.BudgetAttribute;
public class BudgetAttributeDeleted implements Event{

	private boolean success;

	public BudgetAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
