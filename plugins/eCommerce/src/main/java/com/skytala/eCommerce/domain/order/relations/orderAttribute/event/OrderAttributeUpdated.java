package com.skytala.eCommerce.domain.order.relations.orderAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;
public class OrderAttributeUpdated implements Event{

	private boolean success;

	public OrderAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
