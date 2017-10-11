package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;
public class OrderDeliveryScheduleUpdated implements Event{

	private boolean success;

	public OrderDeliveryScheduleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
