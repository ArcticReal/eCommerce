package com.skytala.eCommerce.domain.order.relations.orderContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;
public class OrderContentUpdated implements Event{

	private boolean success;

	public OrderContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
