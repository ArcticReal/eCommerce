package com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.model.OrderItemGroupOrder;
public class OrderItemGroupOrderDeleted implements Event{

	private boolean success;

	public OrderItemGroupOrderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
