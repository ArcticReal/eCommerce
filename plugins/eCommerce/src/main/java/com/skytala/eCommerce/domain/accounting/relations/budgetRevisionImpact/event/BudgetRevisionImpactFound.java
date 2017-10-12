package com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.model.BudgetRevisionImpact;
public class BudgetRevisionImpactFound implements Event{

	private List<BudgetRevisionImpact> budgetRevisionImpacts;

	public BudgetRevisionImpactFound(List<BudgetRevisionImpact> budgetRevisionImpacts) {
		this.budgetRevisionImpacts = budgetRevisionImpacts;
	}

	public List<BudgetRevisionImpact> getBudgetRevisionImpacts()	{
		return budgetRevisionImpacts;
	}

}
