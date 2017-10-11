package com.skytala.eCommerce.domain.order.relations.returnItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.ReturnItem;
public class ReturnItemUpdated implements Event{

	private boolean success;

	public ReturnItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
