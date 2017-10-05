package com.skytala.eCommerce.domain.returnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnType.model.ReturnType;
public class ReturnTypeDeleted implements Event{

	private boolean success;

	public ReturnTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
