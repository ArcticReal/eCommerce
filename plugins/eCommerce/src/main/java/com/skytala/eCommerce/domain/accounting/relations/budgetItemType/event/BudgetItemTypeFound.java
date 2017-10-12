package com.skytala.eCommerce.domain.accounting.relations.budgetItemType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemType.model.BudgetItemType;
public class BudgetItemTypeFound implements Event{

	private List<BudgetItemType> budgetItemTypes;

	public BudgetItemTypeFound(List<BudgetItemType> budgetItemTypes) {
		this.budgetItemTypes = budgetItemTypes;
	}

	public List<BudgetItemType> getBudgetItemTypes()	{
		return budgetItemTypes;
	}

}
