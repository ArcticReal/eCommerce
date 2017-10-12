package com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.model.FinAccountTransAttribute;
public class FinAccountTransAttributeDeleted implements Event{

	private boolean success;

	public FinAccountTransAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
