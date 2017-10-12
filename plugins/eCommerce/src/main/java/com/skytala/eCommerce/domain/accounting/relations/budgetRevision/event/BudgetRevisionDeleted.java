package com.skytala.eCommerce.domain.accounting.relations.budgetRevision.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetRevision.model.BudgetRevision;
public class BudgetRevisionDeleted implements Event{

	private boolean success;

	public BudgetRevisionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
