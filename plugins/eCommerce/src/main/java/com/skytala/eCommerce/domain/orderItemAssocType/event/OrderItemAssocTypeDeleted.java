package com.skytala.eCommerce.domain.orderItemAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderItemAssocType.model.OrderItemAssocType;
public class OrderItemAssocTypeDeleted implements Event{

	private boolean success;

	public OrderItemAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
