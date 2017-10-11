package com.skytala.eCommerce.domain.order.relations.orderNotification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderNotification.model.OrderNotification;
public class OrderNotificationUpdated implements Event{

	private boolean success;

	public OrderNotificationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
