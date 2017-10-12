package com.skytala.eCommerce.domain.accounting.relations.budgetRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRole.model.BudgetRole;
public class BudgetRoleAdded implements Event{

	private BudgetRole addedBudgetRole;
	private boolean success;

	public BudgetRoleAdded(BudgetRole addedBudgetRole, boolean success){
		this.addedBudgetRole = addedBudgetRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetRole getAddedBudgetRole() {
		return addedBudgetRole;
	}

}
