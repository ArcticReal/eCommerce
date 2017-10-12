package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.model.BudgetScenarioApplication;
public class BudgetScenarioApplicationDeleted implements Event{

	private boolean success;

	public BudgetScenarioApplicationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
