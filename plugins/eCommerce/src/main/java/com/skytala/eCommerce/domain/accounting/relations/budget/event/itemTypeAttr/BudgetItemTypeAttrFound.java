package com.skytala.eCommerce.domain.accounting.relations.budget.event.itemTypeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemTypeAttr.BudgetItemTypeAttr;
public class BudgetItemTypeAttrFound implements Event{

	private List<BudgetItemTypeAttr> budgetItemTypeAttrs;

	public BudgetItemTypeAttrFound(List<BudgetItemTypeAttr> budgetItemTypeAttrs) {
		this.budgetItemTypeAttrs = budgetItemTypeAttrs;
	}

	public List<BudgetItemTypeAttr> getBudgetItemTypeAttrs()	{
		return budgetItemTypeAttrs;
	}

}
