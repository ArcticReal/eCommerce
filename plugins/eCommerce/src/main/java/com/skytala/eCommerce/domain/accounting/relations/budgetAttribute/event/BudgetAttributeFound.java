package com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.model.BudgetAttribute;
public class BudgetAttributeFound implements Event{

	private List<BudgetAttribute> budgetAttributes;

	public BudgetAttributeFound(List<BudgetAttribute> budgetAttributes) {
		this.budgetAttributes = budgetAttributes;
	}

	public List<BudgetAttribute> getBudgetAttributes()	{
		return budgetAttributes;
	}

}
