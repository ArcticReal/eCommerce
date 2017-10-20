package com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioRule.BudgetScenarioRule;
public class BudgetScenarioRuleDeleted implements Event{

	private boolean success;

	public BudgetScenarioRuleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
