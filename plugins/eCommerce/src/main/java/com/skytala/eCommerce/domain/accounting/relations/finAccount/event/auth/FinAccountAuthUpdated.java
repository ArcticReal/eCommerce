package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.auth.FinAccountAuth;
public class FinAccountAuthUpdated implements Event{

	private boolean success;

	public FinAccountAuthUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
