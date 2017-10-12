package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.model.BudgetScenarioRule;
public class BudgetScenarioRuleUpdated implements Event{

	private boolean success;

	public BudgetScenarioRuleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
