package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioRule.BudgetScenarioRule;
public class BudgetScenarioRuleFound implements Event{

	private List<BudgetScenarioRule> budgetScenarioRules;

	public BudgetScenarioRuleFound(List<BudgetScenarioRule> budgetScenarioRules) {
		this.budgetScenarioRules = budgetScenarioRules;
	}

	public List<BudgetScenarioRule> getBudgetScenarioRules()	{
		return budgetScenarioRules;
	}

}
