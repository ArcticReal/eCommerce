package com.skytala.eCommerce.domain.order.relations.orderNotification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderNotification.model.OrderNotification;
public class OrderNotificationAdded implements Event{

	private OrderNotification addedOrderNotification;
	private boolean success;

	public OrderNotificationAdded(OrderNotification addedOrderNotification, boolean success){
		this.addedOrderNotification = addedOrderNotification;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderNotification getAddedOrderNotification() {
		return addedOrderNotification;
	}

}
