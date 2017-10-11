package com.skytala.eCommerce.domain.order.relations.custRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.CustRequest;
public class CustRequestDeleted implements Event{

	private boolean success;

	public CustRequestDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
