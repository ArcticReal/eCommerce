package com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.model.FinAccountAuth;
public class FinAccountAuthDeleted implements Event{

	private boolean success;

	public FinAccountAuthDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
