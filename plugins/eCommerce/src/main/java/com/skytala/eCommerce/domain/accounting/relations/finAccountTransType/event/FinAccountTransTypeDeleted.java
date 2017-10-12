package com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.model.FinAccountTransType;
public class FinAccountTransTypeDeleted implements Event{

	private boolean success;

	public FinAccountTransTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
