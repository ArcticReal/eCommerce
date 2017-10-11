package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;
public class OrderDeliveryScheduleAdded implements Event{

	private OrderDeliverySchedule addedOrderDeliverySchedule;
	private boolean success;

	public OrderDeliveryScheduleAdded(OrderDeliverySchedule addedOrderDeliverySchedule, boolean success){
		this.addedOrderDeliverySchedule = addedOrderDeliverySchedule;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderDeliverySchedule getAddedOrderDeliverySchedule() {
		return addedOrderDeliverySchedule;
	}

}
