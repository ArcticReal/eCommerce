package com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.model.CustRequestCommEvent;
public class CustRequestCommEventDeleted implements Event{

	private boolean success;

	public CustRequestCommEventDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
