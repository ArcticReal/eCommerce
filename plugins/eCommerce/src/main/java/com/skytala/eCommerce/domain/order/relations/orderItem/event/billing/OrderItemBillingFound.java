package com.skytala.eCommerce.domain.order.relations.orderItem.event.billing;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.billing.OrderItemBilling;
public class OrderItemBillingFound implements Event{

	private List<OrderItemBilling> orderItemBillings;

	public OrderItemBillingFound(List<OrderItemBilling> orderItemBillings) {
		this.orderItemBillings = orderItemBillings;
	}

	public List<OrderItemBilling> getOrderItemBillings()	{
		return orderItemBillings;
	}

}
