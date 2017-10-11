package com.skytala.eCommerce.domain.order.relations.orderAdjustmentAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentAttribute.model.OrderAdjustmentAttribute;
public class OrderAdjustmentAttributeFound implements Event{

	private List<OrderAdjustmentAttribute> orderAdjustmentAttributes;

	public OrderAdjustmentAttributeFound(List<OrderAdjustmentAttribute> orderAdjustmentAttributes) {
		this.orderAdjustmentAttributes = orderAdjustmentAttributes;
	}

	public List<OrderAdjustmentAttribute> getOrderAdjustmentAttributes()	{
		return orderAdjustmentAttributes;
	}

}
