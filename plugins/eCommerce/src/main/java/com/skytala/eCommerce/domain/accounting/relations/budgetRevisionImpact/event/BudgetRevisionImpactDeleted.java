package com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.model.BudgetRevisionImpact;
public class BudgetRevisionImpactDeleted implements Event{

	private boolean success;

	public BudgetRevisionImpactDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
