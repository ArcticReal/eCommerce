package com.skytala.eCommerce.domain.budgetScenario.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetScenario.model.BudgetScenario;
public class BudgetScenarioFound implements Event{

	private List<BudgetScenario> budgetScenarios;

	public BudgetScenarioFound(List<BudgetScenario> budgetScenarios) {
		this.budgetScenarios = budgetScenarios;
	}

	public List<BudgetScenario> getBudgetScenarios()	{
		return budgetScenarios;
	}

}
