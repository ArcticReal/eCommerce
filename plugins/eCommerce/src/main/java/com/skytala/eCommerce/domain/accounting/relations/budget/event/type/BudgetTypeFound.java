package com.skytala.eCommerce.domain.accounting.relations.budget.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.type.BudgetType;
public class BudgetTypeFound implements Event{

	private List<BudgetType> budgetTypes;

	public BudgetTypeFound(List<BudgetType> budgetTypes) {
		this.budgetTypes = budgetTypes;
	}

	public List<BudgetType> getBudgetTypes()	{
		return budgetTypes;
	}

}
