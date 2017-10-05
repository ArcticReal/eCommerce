package com.skytala.eCommerce.domain.orderItemChange.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderItemChange.model.OrderItemChange;
public class OrderItemChangeFound implements Event{

	private List<OrderItemChange> orderItemChanges;

	public OrderItemChangeFound(List<OrderItemChange> orderItemChanges) {
		this.orderItemChanges = orderItemChanges;
	}

	public List<OrderItemChange> getOrderItemChanges()	{
		return orderItemChanges;
	}

}
