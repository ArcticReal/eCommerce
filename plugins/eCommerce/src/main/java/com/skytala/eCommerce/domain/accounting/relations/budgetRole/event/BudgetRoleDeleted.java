package com.skytala.eCommerce.domain.accounting.relations.budgetRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRole.model.BudgetRole;
public class BudgetRoleDeleted implements Event{

	private boolean success;

	public BudgetRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
