package com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.model.BudgetTypeAttr;
public class BudgetTypeAttrUpdated implements Event{

	private boolean success;

	public BudgetTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
