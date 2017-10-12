package com.skytala.eCommerce.domain.accounting.relations.budgetRevision.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRevision.model.BudgetRevision;
public class BudgetRevisionFound implements Event{

	private List<BudgetRevision> budgetRevisions;

	public BudgetRevisionFound(List<BudgetRevision> budgetRevisions) {
		this.budgetRevisions = budgetRevisions;
	}

	public List<BudgetRevision> getBudgetRevisions()	{
		return budgetRevisions;
	}

}
