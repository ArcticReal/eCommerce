package com.skytala.eCommerce.domain.order.relations.orderItem.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.attribute.OrderItemAttribute;
public class OrderItemAttributeFound implements Event{

	private List<OrderItemAttribute> orderItemAttributes;

	public OrderItemAttributeFound(List<OrderItemAttribute> orderItemAttributes) {
		this.orderItemAttributes = orderItemAttributes;
	}

	public List<OrderItemAttribute> getOrderItemAttributes()	{
		return orderItemAttributes;
	}

}
