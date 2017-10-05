package com.skytala.eCommerce.domain.finAccountType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccountType.model.FinAccountType;
public class FinAccountTypeDeleted implements Event{

	private boolean success;

	public FinAccountTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
