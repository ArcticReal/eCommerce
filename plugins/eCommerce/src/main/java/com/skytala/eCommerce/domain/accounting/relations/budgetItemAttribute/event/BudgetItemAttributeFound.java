package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;
public class BudgetItemAttributeFound implements Event{

	private List<BudgetItemAttribute> budgetItemAttributes;

	public BudgetItemAttributeFound(List<BudgetItemAttribute> budgetItemAttributes) {
		this.budgetItemAttributes = budgetItemAttributes;
	}

	public List<BudgetItemAttribute> getBudgetItemAttributes()	{
		return budgetItemAttributes;
	}

}
