package com.skytala.eCommerce.domain.accounting.relations.budgetItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItem.model.BudgetItem;
public class BudgetItemFound implements Event{

	private List<BudgetItem> budgetItems;

	public BudgetItemFound(List<BudgetItem> budgetItems) {
		this.budgetItems = budgetItems;
	}

	public List<BudgetItem> getBudgetItems()	{
		return budgetItems;
	}

}
