package com.skytala.eCommerce.domain.order.relations.custRequestItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItem.model.CustRequestItem;
public class CustRequestItemUpdated implements Event{

	private boolean success;

	public CustRequestItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
