package com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.model.BudgetAttribute;
public class BudgetAttributeUpdated implements Event{

	private boolean success;

	public BudgetAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
