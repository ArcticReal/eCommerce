package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.GlReconciliation;
public class GlReconciliationDeleted implements Event{

	private boolean success;

	public GlReconciliationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
