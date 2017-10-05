package com.skytala.eCommerce.domain.finAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccount.model.FinAccount;
public class FinAccountDeleted implements Event{

	private boolean success;

	public FinAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
