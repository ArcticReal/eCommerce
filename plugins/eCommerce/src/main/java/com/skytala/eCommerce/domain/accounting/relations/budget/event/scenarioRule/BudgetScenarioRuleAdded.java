package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioRule.BudgetScenarioRule;
public class BudgetScenarioRuleAdded implements Event{

	private BudgetScenarioRule addedBudgetScenarioRule;
	private boolean success;

	public BudgetScenarioRuleAdded(BudgetScenarioRule addedBudgetScenarioRule, boolean success){
		this.addedBudgetScenarioRule = addedBudgetScenarioRule;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetScenarioRule getAddedBudgetScenarioRule() {
		return addedBudgetScenarioRule;
	}

}
