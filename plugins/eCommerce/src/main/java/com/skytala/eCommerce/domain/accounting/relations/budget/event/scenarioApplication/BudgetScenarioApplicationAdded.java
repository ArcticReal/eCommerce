package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioApplication.BudgetScenarioApplication;
public class BudgetScenarioApplicationAdded implements Event{

	private BudgetScenarioApplication addedBudgetScenarioApplication;
	private boolean success;

	public BudgetScenarioApplicationAdded(BudgetScenarioApplication addedBudgetScenarioApplication, boolean success){
		this.addedBudgetScenarioApplication = addedBudgetScenarioApplication;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetScenarioApplication getAddedBudgetScenarioApplication() {
		return addedBudgetScenarioApplication;
	}

}
