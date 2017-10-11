package com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.model.OrderItemAttribute;
public class OrderItemAttributeFound implements Event{

	private List<OrderItemAttribute> orderItemAttributes;

	public OrderItemAttributeFound(List<OrderItemAttribute> orderItemAttributes) {
		this.orderItemAttributes = orderItemAttributes;
	}

	public List<OrderItemAttribute> getOrderItemAttributes()	{
		return orderItemAttributes;
	}

}
