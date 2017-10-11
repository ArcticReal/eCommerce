package com.skytala.eCommerce.domain.order.relations.returnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnType.model.ReturnType;
public class ReturnTypeUpdated implements Event{

	private boolean success;

	public ReturnTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
