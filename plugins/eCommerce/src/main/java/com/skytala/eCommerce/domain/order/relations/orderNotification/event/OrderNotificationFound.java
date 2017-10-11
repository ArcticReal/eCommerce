package com.skytala.eCommerce.domain.order.relations.orderNotification.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderNotification.model.OrderNotification;
public class OrderNotificationFound implements Event{

	private List<OrderNotification> orderNotifications;

	public OrderNotificationFound(List<OrderNotification> orderNotifications) {
		this.orderNotifications = orderNotifications;
	}

	public List<OrderNotification> getOrderNotifications()	{
		return orderNotifications;
	}

}
