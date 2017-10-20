package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.type.OrderAdjustmentType;
public class OrderAdjustmentTypeUpdated implements Event{

	private boolean success;

	public OrderAdjustmentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
