package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history.GlAccountHistory;
public class GlAccountHistoryDeleted implements Event{

	private boolean success;

	public GlAccountHistoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
