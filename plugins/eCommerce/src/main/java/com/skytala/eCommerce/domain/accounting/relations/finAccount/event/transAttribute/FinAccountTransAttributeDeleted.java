package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute.FinAccountTransAttribute;
public class FinAccountTransAttributeDeleted implements Event{

	private boolean success;

	public FinAccountTransAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
