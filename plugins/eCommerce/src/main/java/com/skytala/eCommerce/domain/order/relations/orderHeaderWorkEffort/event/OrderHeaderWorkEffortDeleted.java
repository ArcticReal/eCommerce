package com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.model.OrderHeaderWorkEffort;
public class OrderHeaderWorkEffortDeleted implements Event{

	private boolean success;

	public OrderHeaderWorkEffortDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
