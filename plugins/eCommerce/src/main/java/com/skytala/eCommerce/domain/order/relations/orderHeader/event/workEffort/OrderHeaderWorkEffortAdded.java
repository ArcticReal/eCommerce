package com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;
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
