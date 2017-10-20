package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenario.BudgetScenario;
public class BudgetScenarioFound implements Event{

	private List<BudgetScenario> budgetScenarios;

	public BudgetScenarioFound(List<BudgetScenario> budgetScenarios) {
		this.budgetScenarios = budgetScenarios;
	}

	public List<BudgetScenario> getBudgetScenarios()	{
		return budgetScenarios;
	}

}
