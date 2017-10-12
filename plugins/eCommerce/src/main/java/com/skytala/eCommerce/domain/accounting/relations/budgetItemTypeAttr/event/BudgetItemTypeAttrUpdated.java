package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;
public class BudgetItemTypeAttrUpdated implements Event{

	private boolean success;

	public BudgetItemTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
