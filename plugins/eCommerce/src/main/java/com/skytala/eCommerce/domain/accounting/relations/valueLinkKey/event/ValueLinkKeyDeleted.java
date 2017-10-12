package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;
public class ValueLinkKeyDeleted implements Event{

	private boolean success;

	public ValueLinkKeyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
