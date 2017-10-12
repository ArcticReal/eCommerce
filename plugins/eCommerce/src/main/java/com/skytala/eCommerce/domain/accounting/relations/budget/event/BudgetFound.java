package com.skytala.eCommerce.domain.accounting.relations.budget.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.Budget;
public class BudgetFound implements Event{

	private List<Budget> budgets;

	public BudgetFound(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public List<Budget> getBudgets()	{
		return budgets;
	}

}
