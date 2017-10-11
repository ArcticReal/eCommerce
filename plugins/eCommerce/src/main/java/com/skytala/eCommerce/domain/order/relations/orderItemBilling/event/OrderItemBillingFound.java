package com.skytala.eCommerce.domain.order.relations.orderItemBilling.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;
public class OrderItemBillingFound implements Event{

	private List<OrderItemBilling> orderItemBillings;

	public OrderItemBillingFound(List<OrderItemBilling> orderItemBillings) {
		this.orderItemBillings = orderItemBillings;
	}

	public List<OrderItemBilling> getOrderItemBillings()	{
		return orderItemBillings;
	}

}
