package com.skytala.eCommerce.domain.order.relations.orderItemBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;
public class OrderItemBillingAdded implements Event{

	private OrderItemBilling addedOrderItemBilling;
	private boolean success;

	public OrderItemBillingAdded(OrderItemBilling addedOrderItemBilling, boolean success){
		this.addedOrderItemBilling = addedOrderItemBilling;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemBilling getAddedOrderItemBilling() {
		return addedOrderItemBilling;
	}

}
