package com.skytala.eCommerce.domain.accounting.relations.budget.event.itemTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemTypeAttr.BudgetItemTypeAttr;
public class BudgetItemTypeAttrDeleted implements Event{

	private boolean success;

	public BudgetItemTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
