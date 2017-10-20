package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenario.BudgetScenario;
public class BudgetScenarioDeleted implements Event{

	private boolean success;

	public BudgetScenarioDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
