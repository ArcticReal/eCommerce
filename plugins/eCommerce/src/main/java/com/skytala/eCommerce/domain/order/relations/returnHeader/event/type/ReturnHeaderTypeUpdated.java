package com.skytala.eCommerce.domain.order.relations.returnHeader.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnHeader.model.type.ReturnHeaderType;
public class ReturnHeaderTypeUpdated implements Event{

	private boolean success;

	public ReturnHeaderTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
