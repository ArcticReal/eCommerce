package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model.FinAccountStatus;
public class FinAccountStatusUpdated implements Event{

	private boolean success;

	public FinAccountStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}