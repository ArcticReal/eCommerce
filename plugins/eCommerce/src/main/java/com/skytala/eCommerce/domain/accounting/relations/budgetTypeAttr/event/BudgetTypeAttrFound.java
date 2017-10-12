package com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.model.BudgetTypeAttr;
public class BudgetTypeAttrFound implements Event{

	private List<BudgetTypeAttr> budgetTypeAttrs;

	public BudgetTypeAttrFound(List<BudgetTypeAttr> budgetTypeAttrs) {
		this.budgetTypeAttrs = budgetTypeAttrs;
	}

	public List<BudgetTypeAttr> getBudgetTypeAttrs()	{
		return budgetTypeAttrs;
	}

}
