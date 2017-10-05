package com.skytala.eCommerce.domain.orderAdjustmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderAdjustmentType.model.OrderAdjustmentType;
public class OrderAdjustmentTypeUpdated implements Event{

	private boolean success;

	public OrderAdjustmentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
