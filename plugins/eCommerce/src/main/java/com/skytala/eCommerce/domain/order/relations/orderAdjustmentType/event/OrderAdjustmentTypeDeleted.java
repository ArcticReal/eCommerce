package com.skytala.eCommerce.domain.order.relations.orderAdjustmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentType.model.OrderAdjustmentType;
public class OrderAdjustmentTypeDeleted implements Event{

	private boolean success;

	public OrderAdjustmentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
