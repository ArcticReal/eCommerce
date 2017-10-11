package com.skytala.eCommerce.domain.order.relations.returnHeader.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;
public class ReturnHeaderUpdated implements Event{

	private boolean success;

	public ReturnHeaderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
