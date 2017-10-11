package com.skytala.eCommerce.domain.order.relations.orderTermAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTermAttribute.model.OrderTermAttribute;
public class OrderTermAttributeFound implements Event{

	private List<OrderTermAttribute> orderTermAttributes;

	public OrderTermAttributeFound(List<OrderTermAttribute> orderTermAttributes) {
		this.orderTermAttributes = orderTermAttributes;
	}

	public List<OrderTermAttribute> getOrderTermAttributes()	{
		return orderTermAttributes;
	}

}
