package com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute.BudgetAttribute;
public class BudgetAttributeFound implements Event{

	private List<BudgetAttribute> budgetAttributes;

	public BudgetAttributeFound(List<BudgetAttribute> budgetAttributes) {
		this.budgetAttributes = budgetAttributes;
	}

	public List<BudgetAttribute> getBudgetAttributes()	{
		return budgetAttributes;
	}

}
