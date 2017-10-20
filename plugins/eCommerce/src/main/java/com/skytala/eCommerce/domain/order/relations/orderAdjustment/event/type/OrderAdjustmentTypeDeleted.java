package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.type.OrderAdjustmentType;
public class OrderAdjustmentTypeDeleted implements Event{

	private boolean success;

	public OrderAdjustmentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
