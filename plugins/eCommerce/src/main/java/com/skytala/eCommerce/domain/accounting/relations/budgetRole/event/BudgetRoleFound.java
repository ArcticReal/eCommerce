package com.skytala.eCommerce.domain.accounting.relations.budgetRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRole.model.BudgetRole;
public class BudgetRoleFound implements Event{

	private List<BudgetRole> budgetRoles;

	public BudgetRoleFound(List<BudgetRole> budgetRoles) {
		this.budgetRoles = budgetRoles;
	}

	public List<BudgetRole> getBudgetRoles()	{
		return budgetRoles;
	}

}
