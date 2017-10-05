package com.skytala.eCommerce.domain.orderContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderContentType.model.OrderContentType;
public class OrderContentTypeDeleted implements Event{

	private boolean success;

	public OrderContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
