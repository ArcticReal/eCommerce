package com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;
public class OrderTermAttributeDeleted implements Event{

	private boolean success;

	public OrderTermAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
