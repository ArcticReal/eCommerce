package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;
public class BudgetItemTypeAttrFound implements Event{

	private List<BudgetItemTypeAttr> budgetItemTypeAttrs;

	public BudgetItemTypeAttrFound(List<BudgetItemTypeAttr> budgetItemTypeAttrs) {
		this.budgetItemTypeAttrs = budgetItemTypeAttrs;
	}

	public List<BudgetItemTypeAttr> getBudgetItemTypeAttrs()	{
		return budgetItemTypeAttrs;
	}

}
