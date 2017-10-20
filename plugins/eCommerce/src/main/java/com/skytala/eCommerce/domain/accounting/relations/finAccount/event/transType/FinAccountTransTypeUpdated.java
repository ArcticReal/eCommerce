package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transType.FinAccountTransType;
public class FinAccountTransTypeUpdated implements Event{

	private boolean success;

	public FinAccountTransTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
