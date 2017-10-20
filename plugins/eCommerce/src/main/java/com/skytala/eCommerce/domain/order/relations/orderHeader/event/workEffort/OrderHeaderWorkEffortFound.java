package com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;
public class OrderHeaderWorkEffortFound implements Event{

	private List<OrderHeaderWorkEffort> orderHeaderWorkEfforts;

	public OrderHeaderWorkEffortFound(List<OrderHeaderWorkEffort> orderHeaderWorkEfforts) {
		this.orderHeaderWorkEfforts = orderHeaderWorkEfforts;
	}

	public List<OrderHeaderWorkEffort> getOrderHeaderWorkEfforts()	{
		return orderHeaderWorkEfforts;
	}

}
