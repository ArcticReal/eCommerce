package com.skytala.eCommerce.domain.accounting.relations.glAccountHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountHistory.model.GlAccountHistory;
public class GlAccountHistoryUpdated implements Event{

	private boolean success;

	public GlAccountHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
