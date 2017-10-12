package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.GlReconciliation;
public class GlReconciliationUpdated implements Event{

	private boolean success;

	public GlReconciliationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
