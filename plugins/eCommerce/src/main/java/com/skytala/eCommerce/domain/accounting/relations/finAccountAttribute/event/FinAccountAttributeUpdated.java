package com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.model.FinAccountAttribute;
public class FinAccountAttributeUpdated implements Event{

	private boolean success;

	public FinAccountAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
