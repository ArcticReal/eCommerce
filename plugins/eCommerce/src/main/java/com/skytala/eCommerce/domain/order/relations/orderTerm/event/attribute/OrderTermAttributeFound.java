package com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;
public class OrderTermAttributeFound implements Event{

	private List<OrderTermAttribute> orderTermAttributes;

	public OrderTermAttributeFound(List<OrderTermAttribute> orderTermAttributes) {
		this.orderTermAttributes = orderTermAttributes;
	}

	public List<OrderTermAttribute> getOrderTermAttributes()	{
		return orderTermAttributes;
	}

}
