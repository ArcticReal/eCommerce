package com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.model.GlReconciliationEntry;
public class GlReconciliationEntryUpdated implements Event{

	private boolean success;

	public GlReconciliationEntryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
