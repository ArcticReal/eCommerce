package com.skytala.eCommerce.domain.order.relations.returnHeaderType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnHeaderType.model.ReturnHeaderType;
public class ReturnHeaderTypeUpdated implements Event{

	private boolean success;

	public ReturnHeaderTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
