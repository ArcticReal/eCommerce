package com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.model.OrderAdjustmentBilling;
public class OrderAdjustmentBillingFound implements Event{

	private List<OrderAdjustmentBilling> orderAdjustmentBillings;

	public OrderAdjustmentBillingFound(List<OrderAdjustmentBilling> orderAdjustmentBillings) {
		this.orderAdjustmentBillings = orderAdjustmentBillings;
	}

	public List<OrderAdjustmentBilling> getOrderAdjustmentBillings()	{
		return orderAdjustmentBillings;
	}

}
