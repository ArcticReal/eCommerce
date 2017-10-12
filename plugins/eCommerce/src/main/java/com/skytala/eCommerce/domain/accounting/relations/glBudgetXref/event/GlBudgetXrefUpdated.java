package com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.model.GlBudgetXref;
public class GlBudgetXrefUpdated implements Event{

	private boolean success;

	public GlBudgetXrefUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
