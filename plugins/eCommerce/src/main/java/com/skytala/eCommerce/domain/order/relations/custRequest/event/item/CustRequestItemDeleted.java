package com.skytala.eCommerce.domain.order.relations.custRequest.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.item.CustRequestItem;
public class CustRequestItemDeleted implements Event{

	private boolean success;

	public CustRequestItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
