package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.entry.GlReconciliationEntry;
public class GlReconciliationEntryDeleted implements Event{

	private boolean success;

	public GlReconciliationEntryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
