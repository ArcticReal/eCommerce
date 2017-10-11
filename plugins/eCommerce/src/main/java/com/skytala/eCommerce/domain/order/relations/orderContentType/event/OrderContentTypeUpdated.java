package com.skytala.eCommerce.domain.order.relations.orderContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContentType.model.OrderContentType;
public class OrderContentTypeUpdated implements Event{

	private boolean success;

	public OrderContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
