package com.skytala.eCommerce.domain.accounting.relations.budgetStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetStatus.model.BudgetStatus;
public class BudgetStatusFound implements Event{

	private List<BudgetStatus> budgetStatuss;

	public BudgetStatusFound(List<BudgetStatus> budgetStatuss) {
		this.budgetStatuss = budgetStatuss;
	}

	public List<BudgetStatus> getBudgetStatuss()	{
		return budgetStatuss;
	}

}
