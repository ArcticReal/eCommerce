package com.skytala.eCommerce.domain.orderAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderAdjustment.model.OrderAdjustment;
public class OrderAdjustmentDeleted implements Event{

	private boolean success;

	public OrderAdjustmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
