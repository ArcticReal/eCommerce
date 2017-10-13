package com.skytala.eCommerce.domain.accounting.relations.finAccountType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountType.model.FinAccountType;
public class FinAccountTypeUpdated implements Event{

	private boolean success;

	public FinAccountTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}