package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.type.OrderAdjustmentType;
public class OrderAdjustmentTypeFound implements Event{

	private List<OrderAdjustmentType> orderAdjustmentTypes;

	public OrderAdjustmentTypeFound(List<OrderAdjustmentType> orderAdjustmentTypes) {
		this.orderAdjustmentTypes = orderAdjustmentTypes;
	}

	public List<OrderAdjustmentType> getOrderAdjustmentTypes()	{
		return orderAdjustmentTypes;
	}

}
