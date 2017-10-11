package com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.model.OrderHeaderWorkEffort;
public class OrderHeaderWorkEffortAdded implements Event{

	private OrderHeaderWorkEffort addedOrderHeaderWorkEffort;
	private boolean success;

	public OrderHeaderWorkEffortAdded(OrderHeaderWorkEffort addedOrderHeaderWorkEffort, boolean success){
		this.addedOrderHeaderWorkEffort = addedOrderHeaderWorkEffort;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderHeaderWorkEffort getAddedOrderHeaderWorkEffort() {
		return addedOrderHeaderWorkEffort;
	}

}
