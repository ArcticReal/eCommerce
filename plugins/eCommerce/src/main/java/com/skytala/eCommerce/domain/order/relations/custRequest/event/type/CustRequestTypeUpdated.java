package com.skytala.eCommerce.domain.order.relations.custRequest.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.type.CustRequestType;
public class CustRequestTypeUpdated implements Event{

	private boolean success;

	public CustRequestTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
