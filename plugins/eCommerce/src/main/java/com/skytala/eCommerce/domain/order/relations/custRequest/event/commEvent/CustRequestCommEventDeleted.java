package com.skytala.eCommerce.domain.order.relations.custRequest.event.commEvent;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.commEvent.CustRequestCommEvent;
public class CustRequestCommEventDeleted implements Event{

	private boolean success;

	public CustRequestCommEventDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
