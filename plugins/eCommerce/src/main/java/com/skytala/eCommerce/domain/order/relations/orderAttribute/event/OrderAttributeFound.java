package com.skytala.eCommerce.domain.order.relations.orderAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;
public class OrderAttributeFound implements Event{

	private List<OrderAttribute> orderAttributes;

	public OrderAttributeFound(List<OrderAttribute> orderAttributes) {
		this.orderAttributes = orderAttributes;
	}

	public List<OrderAttribute> getOrderAttributes()	{
		return orderAttributes;
	}

}
