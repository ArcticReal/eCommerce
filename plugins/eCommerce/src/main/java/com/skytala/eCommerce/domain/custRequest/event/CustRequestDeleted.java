package com.skytala.eCommerce.domain.custRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequest.model.CustRequest;
public class CustRequestDeleted implements Event{

	private boolean success;

	public CustRequestDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
