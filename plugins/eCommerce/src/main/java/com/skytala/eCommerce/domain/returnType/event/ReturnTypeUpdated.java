package com.skytala.eCommerce.domain.returnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnType.model.ReturnType;
public class ReturnTypeUpdated implements Event{

	private boolean success;

	public ReturnTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
