package com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.assocType.OrderItemAssocType;
public class OrderItemAssocTypeDeleted implements Event{

	private boolean success;

	public OrderItemAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
