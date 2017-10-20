package com.skytala.eCommerce.domain.accounting.relations.budget.event.revision;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.revision.BudgetRevision;
public class BudgetRevisionAdded implements Event{

	private BudgetRevision addedBudgetRevision;
	private boolean success;

	public BudgetRevisionAdded(BudgetRevision addedBudgetRevision, boolean success){
		this.addedBudgetRevision = addedBudgetRevision;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetRevision getAddedBudgetRevision() {
		return addedBudgetRevision;
	}

}
