package com.skytala.eCommerce.domain.budgetScenario.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetScenario.model.BudgetScenario;
public class BudgetScenarioAdded implements Event{

	private BudgetScenario addedBudgetScenario;
	private boolean success;

	public BudgetScenarioAdded(BudgetScenario addedBudgetScenario, boolean success){
		this.addedBudgetScenario = addedBudgetScenario;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetScenario getAddedBudgetScenario() {
		return addedBudgetScenario;
	}

}
