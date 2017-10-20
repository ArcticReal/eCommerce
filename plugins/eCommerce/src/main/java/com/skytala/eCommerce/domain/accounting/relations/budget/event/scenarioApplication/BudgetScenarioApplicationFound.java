package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioApplication.BudgetScenarioApplication;
public class BudgetScenarioApplicationFound implements Event{

	private List<BudgetScenarioApplication> budgetScenarioApplications;

	public BudgetScenarioApplicationFound(List<BudgetScenarioApplication> budgetScenarioApplications) {
		this.budgetScenarioApplications = budgetScenarioApplications;
	}

	public List<BudgetScenarioApplication> getBudgetScenarioApplications()	{
		return budgetScenarioApplications;
	}

}
