package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenario.BudgetScenario;
public class BudgetScenarioUpdated implements Event{

	private boolean success;

	public BudgetScenarioUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
