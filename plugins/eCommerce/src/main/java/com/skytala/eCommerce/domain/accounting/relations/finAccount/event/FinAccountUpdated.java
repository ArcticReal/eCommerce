package com.skytala.eCommerce.domain.accounting.relations.finAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;
public class FinAccountUpdated implements Event{

	private boolean success;

	public FinAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
