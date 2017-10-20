package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.auth.FinAccountAuth;
public class FinAccountAuthDeleted implements Event{

	private boolean success;

	public FinAccountAuthDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
