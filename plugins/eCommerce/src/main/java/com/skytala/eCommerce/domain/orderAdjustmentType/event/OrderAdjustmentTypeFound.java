package com.skytala.eCommerce.domain.orderAdjustmentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderAdjustmentType.model.OrderAdjustmentType;
public class OrderAdjustmentTypeFound implements Event{

	private List<OrderAdjustmentType> orderAdjustmentTypes;

	public OrderAdjustmentTypeFound(List<OrderAdjustmentType> orderAdjustmentTypes) {
		this.orderAdjustmentTypes = orderAdjustmentTypes;
	}

	public List<OrderAdjustmentType> getOrderAdjustmentTypes()	{
		return orderAdjustmentTypes;
	}

}
