package com.skytala.eCommerce.domain.accounting.relations.budget.event.revisionImpact;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.revisionImpact.BudgetRevisionImpact;
public class BudgetRevisionImpactDeleted implements Event{

	private boolean success;

	public BudgetRevisionImpactDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
