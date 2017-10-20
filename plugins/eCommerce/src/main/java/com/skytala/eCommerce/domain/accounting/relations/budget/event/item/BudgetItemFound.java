package com.skytala.eCommerce.domain.accounting.relations.budget.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.item.BudgetItem;
public class BudgetItemFound implements Event{

	private List<BudgetItem> budgetItems;

	public BudgetItemFound(List<BudgetItem> budgetItems) {
		this.budgetItems = budgetItems;
	}

	public List<BudgetItem> getBudgetItems()	{
		return budgetItems;
	}

}
