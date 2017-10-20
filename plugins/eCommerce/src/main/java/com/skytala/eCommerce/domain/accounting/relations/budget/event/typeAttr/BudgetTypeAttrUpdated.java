package com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.typeAttr.BudgetTypeAttr;
public class BudgetTypeAttrUpdated implements Event{

	private boolean success;

	public BudgetTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
