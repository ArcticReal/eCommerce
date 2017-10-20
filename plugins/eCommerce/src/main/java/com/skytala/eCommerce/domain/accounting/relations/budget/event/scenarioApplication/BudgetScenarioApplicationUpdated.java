package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioApplication.BudgetScenarioApplication;
public class BudgetScenarioApplicationUpdated implements Event{

	private boolean success;

	public BudgetScenarioApplicationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
