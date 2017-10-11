package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;
public class OrderDeliveryScheduleDeleted implements Event{

	private boolean success;

	public OrderDeliveryScheduleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
