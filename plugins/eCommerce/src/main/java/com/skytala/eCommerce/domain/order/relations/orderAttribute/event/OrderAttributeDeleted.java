package com.skytala.eCommerce.domain.order.relations.orderAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;
public class OrderAttributeDeleted implements Event{

	private boolean success;

	public OrderAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
