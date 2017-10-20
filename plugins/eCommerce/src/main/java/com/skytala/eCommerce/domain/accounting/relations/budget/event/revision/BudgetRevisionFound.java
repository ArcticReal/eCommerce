package com.skytala.eCommerce.domain.accounting.relations.budget.event.revision;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.revision.BudgetRevision;
public class BudgetRevisionFound implements Event{

	private List<BudgetRevision> budgetRevisions;

	public BudgetRevisionFound(List<BudgetRevision> budgetRevisions) {
		this.budgetRevisions = budgetRevisions;
	}

	public List<BudgetRevision> getBudgetRevisions()	{
		return budgetRevisions;
	}

}
