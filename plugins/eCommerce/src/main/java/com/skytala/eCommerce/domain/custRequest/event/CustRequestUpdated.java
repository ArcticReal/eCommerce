package com.skytala.eCommerce.domain.custRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequest.model.CustRequest;
public class CustRequestUpdated implements Event{

	private boolean success;

	public CustRequestUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
