package com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.model.OrderItemAttribute;
public class OrderItemAttributeUpdated implements Event{

	private boolean success;

	public OrderItemAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
