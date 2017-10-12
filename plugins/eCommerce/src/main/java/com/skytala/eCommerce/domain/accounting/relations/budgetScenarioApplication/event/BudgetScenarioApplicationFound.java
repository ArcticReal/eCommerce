package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.model.BudgetScenarioApplication;
public class BudgetScenarioApplicationFound implements Event{

	private List<BudgetScenarioApplication> budgetScenarioApplications;

	public BudgetScenarioApplicationFound(List<BudgetScenarioApplication> budgetScenarioApplications) {
		this.budgetScenarioApplications = budgetScenarioApplications;
	}

	public List<BudgetScenarioApplication> getBudgetScenarioApplications()	{
		return budgetScenarioApplications;
	}

}
