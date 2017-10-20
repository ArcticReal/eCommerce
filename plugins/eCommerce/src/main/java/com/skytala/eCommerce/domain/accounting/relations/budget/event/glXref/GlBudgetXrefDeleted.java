package com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.glXref.GlBudgetXref;
public class GlBudgetXrefDeleted implements Event{

	private boolean success;

	public GlBudgetXrefDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
