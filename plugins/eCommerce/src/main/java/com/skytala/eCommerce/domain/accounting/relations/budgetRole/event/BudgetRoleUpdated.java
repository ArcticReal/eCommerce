package com.skytala.eCommerce.domain.accounting.relations.budgetRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRole.model.BudgetRole;
public class BudgetRoleUpdated implements Event{

	private boolean success;

	public BudgetRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
