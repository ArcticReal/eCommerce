package com.skytala.eCommerce.domain.accounting.relations.budgetScenario.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetScenario.model.BudgetScenario;
public class BudgetScenarioUpdated implements Event{

	private boolean success;

	public BudgetScenarioUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
