package com.skytala.eCommerce.domain.order.relations.orderItem.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.attribute.OrderItemAttribute;
public class OrderItemAttributeDeleted implements Event{

	private boolean success;

	public OrderItemAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
