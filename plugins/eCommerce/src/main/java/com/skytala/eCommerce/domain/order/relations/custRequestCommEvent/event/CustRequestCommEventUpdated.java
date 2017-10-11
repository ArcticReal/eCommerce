package com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.model.CustRequestCommEvent;
public class CustRequestCommEventUpdated implements Event{

	private boolean success;

	public CustRequestCommEventUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
