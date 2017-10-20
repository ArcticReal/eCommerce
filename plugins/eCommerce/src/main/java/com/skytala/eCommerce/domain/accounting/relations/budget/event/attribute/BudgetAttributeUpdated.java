package com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute.BudgetAttribute;
public class BudgetAttributeUpdated implements Event{

	private boolean success;

	public BudgetAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
