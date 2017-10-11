package com.skytala.eCommerce.domain.order.relations.orderTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTermAttribute.model.OrderTermAttribute;
public class OrderTermAttributeUpdated implements Event{

	private boolean success;

	public OrderTermAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
