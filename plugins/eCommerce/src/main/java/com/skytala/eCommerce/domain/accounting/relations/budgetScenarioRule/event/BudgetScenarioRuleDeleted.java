package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.model.BudgetScenarioRule;
public class BudgetScenarioRuleDeleted implements Event{

	private boolean success;

	public BudgetScenarioRuleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
