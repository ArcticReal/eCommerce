package com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.model.OrderItemAttribute;
public class OrderItemAttributeDeleted implements Event{

	private boolean success;

	public OrderItemAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
