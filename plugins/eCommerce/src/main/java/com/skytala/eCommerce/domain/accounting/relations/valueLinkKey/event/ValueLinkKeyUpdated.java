package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;
public class ValueLinkKeyUpdated implements Event{

	private boolean success;

	public ValueLinkKeyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
