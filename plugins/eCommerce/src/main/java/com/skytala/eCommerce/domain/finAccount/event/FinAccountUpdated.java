package com.skytala.eCommerce.domain.finAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccount.model.FinAccount;
public class FinAccountUpdated implements Event{

	private boolean success;

	public FinAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
