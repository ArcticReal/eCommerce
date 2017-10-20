package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioRule.BudgetScenarioRule;
public class BudgetScenarioRuleUpdated implements Event{

	private boolean success;

	public BudgetScenarioRuleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
