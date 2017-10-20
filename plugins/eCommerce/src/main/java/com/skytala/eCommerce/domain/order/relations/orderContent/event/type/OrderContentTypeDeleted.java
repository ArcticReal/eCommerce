package com.skytala.eCommerce.domain.order.relations.orderContent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;
public class OrderContentTypeDeleted implements Event{

	private boolean success;

	public OrderContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
