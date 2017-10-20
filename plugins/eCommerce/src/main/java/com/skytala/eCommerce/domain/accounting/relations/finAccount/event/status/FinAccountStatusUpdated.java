package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.status.FinAccountStatus;
public class FinAccountStatusUpdated implements Event{

	private boolean success;

	public FinAccountStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}