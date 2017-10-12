package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.model.BudgetScenarioApplication;
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
