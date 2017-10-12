package com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.model.FinAccountAuth;
public class FinAccountAuthUpdated implements Event{

	private boolean success;

	public FinAccountAuthUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
