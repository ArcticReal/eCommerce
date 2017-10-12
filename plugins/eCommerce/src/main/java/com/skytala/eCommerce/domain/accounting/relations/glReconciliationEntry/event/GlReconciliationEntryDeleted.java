package com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.model.GlReconciliationEntry;
public class GlReconciliationEntryDeleted implements Event{

	private boolean success;

	public GlReconciliationEntryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
