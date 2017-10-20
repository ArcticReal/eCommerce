package com.skytala.eCommerce.domain.accounting.relations.budget.event.revisionImpact;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.revisionImpact.BudgetRevisionImpact;
public class BudgetRevisionImpactAdded implements Event{

	private BudgetRevisionImpact addedBudgetRevisionImpact;
	private boolean success;

	public BudgetRevisionImpactAdded(BudgetRevisionImpact addedBudgetRevisionImpact, boolean success){
		this.addedBudgetRevisionImpact = addedBudgetRevisionImpact;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetRevisionImpact getAddedBudgetRevisionImpact() {
		return addedBudgetRevisionImpact;
	}

}
