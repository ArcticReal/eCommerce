package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.OrderAdjustment;
public class OrderAdjustmentUpdated implements Event{

	private boolean success;

	public OrderAdjustmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
