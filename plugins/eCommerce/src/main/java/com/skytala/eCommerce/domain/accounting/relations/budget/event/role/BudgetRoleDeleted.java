package com.skytala.eCommerce.domain.accounting.relations.budget.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.role.BudgetRole;
public class BudgetRoleDeleted implements Event{

	private boolean success;

	public BudgetRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
