package com.skytala.eCommerce.domain.order.relations.orderStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderStatus.model.OrderStatus;
public class OrderStatusAdded implements Event{

	private OrderStatus addedOrderStatus;
	private boolean success;

	public OrderStatusAdded(OrderStatus addedOrderStatus, boolean success){
		this.addedOrderStatus = addedOrderStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderStatus getAddedOrderStatus() {
		return addedOrderStatus;
	}

}
