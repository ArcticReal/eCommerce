package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute.OrderAdjustmentAttribute;
public class OrderAdjustmentAttributeFound implements Event{

	private List<OrderAdjustmentAttribute> orderAdjustmentAttributes;

	public OrderAdjustmentAttributeFound(List<OrderAdjustmentAttribute> orderAdjustmentAttributes) {
		this.orderAdjustmentAttributes = orderAdjustmentAttributes;
	}

	public List<OrderAdjustmentAttribute> getOrderAdjustmentAttributes()	{
		return orderAdjustmentAttributes;
	}

}
