package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.type.FinAccountType;
public class FinAccountTypeDeleted implements Event{

	private boolean success;

	public FinAccountTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
