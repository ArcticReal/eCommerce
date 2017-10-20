package com.skytala.eCommerce.domain.order.relations.orderContent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;
public class OrderContentTypeUpdated implements Event{

	private boolean success;

	public OrderContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
