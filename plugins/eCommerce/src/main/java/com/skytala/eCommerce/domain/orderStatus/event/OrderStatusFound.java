package com.skytala.eCommerce.domain.orderStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderStatus.model.OrderStatus;
public class OrderStatusFound implements Event{

	private List<OrderStatus> orderStatuss;

	public OrderStatusFound(List<OrderStatus> orderStatuss) {
		this.orderStatuss = orderStatuss;
	}

	public List<OrderStatus> getOrderStatuss()	{
		return orderStatuss;
	}

}
