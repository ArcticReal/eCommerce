package com.skytala.eCommerce.domain.budgetScenario.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetScenario.model.BudgetScenario;
public class BudgetScenarioDeleted implements Event{

	private boolean success;

	public BudgetScenarioDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
