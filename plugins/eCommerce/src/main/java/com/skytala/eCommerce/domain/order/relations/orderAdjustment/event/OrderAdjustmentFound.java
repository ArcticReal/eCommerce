package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.OrderAdjustment;
public class OrderAdjustmentFound implements Event{

	private List<OrderAdjustment> orderAdjustments;

	public OrderAdjustmentFound(List<OrderAdjustment> orderAdjustments) {
		this.orderAdjustments = orderAdjustments;
	}

	public List<OrderAdjustment> getOrderAdjustments()	{
		return orderAdjustments;
	}

}
