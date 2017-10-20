package com.skytala.eCommerce.domain.order.relations.returnHeader.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnHeader.model.type.ReturnHeaderType;
public class ReturnHeaderTypeDeleted implements Event{

	private boolean success;

	public ReturnHeaderTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
