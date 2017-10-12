package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;
public class BudgetItemTypeAttrDeleted implements Event{

	private boolean success;

	public BudgetItemTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
