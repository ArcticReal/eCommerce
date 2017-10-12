package com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.model.BudgetRevisionImpact;
public class BudgetRevisionImpactUpdated implements Event{

	private boolean success;

	public BudgetRevisionImpactUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
