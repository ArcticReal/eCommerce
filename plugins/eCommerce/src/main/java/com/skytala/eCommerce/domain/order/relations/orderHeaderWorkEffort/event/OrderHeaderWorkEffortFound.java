package com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.model.OrderHeaderWorkEffort;
public class OrderHeaderWorkEffortFound implements Event{

	private List<OrderHeaderWorkEffort> orderHeaderWorkEfforts;

	public OrderHeaderWorkEffortFound(List<OrderHeaderWorkEffort> orderHeaderWorkEfforts) {
		this.orderHeaderWorkEfforts = orderHeaderWorkEfforts;
	}

	public List<OrderHeaderWorkEffort> getOrderHeaderWorkEfforts()	{
		return orderHeaderWorkEfforts;
	}

}
