package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;
public class OrderDeliveryScheduleFound implements Event{

	private List<OrderDeliverySchedule> orderDeliverySchedules;

	public OrderDeliveryScheduleFound(List<OrderDeliverySchedule> orderDeliverySchedules) {
		this.orderDeliverySchedules = orderDeliverySchedules;
	}

	public List<OrderDeliverySchedule> getOrderDeliverySchedules()	{
		return orderDeliverySchedules;
	}

}
