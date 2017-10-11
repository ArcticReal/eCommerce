package com.skytala.eCommerce.domain.order.relations.orderNotification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderNotification.model.OrderNotification;
public class OrderNotificationDeleted implements Event{

	private boolean success;

	public OrderNotificationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
