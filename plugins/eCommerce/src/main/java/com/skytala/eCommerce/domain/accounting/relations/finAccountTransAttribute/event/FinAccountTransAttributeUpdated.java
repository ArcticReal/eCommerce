package com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.model.FinAccountTransAttribute;
public class FinAccountTransAttributeUpdated implements Event{

	private boolean success;

	public FinAccountTransAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
