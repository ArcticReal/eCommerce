package com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;
public class OrderTermAttributeUpdated implements Event{

	private boolean success;

	public OrderTermAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
