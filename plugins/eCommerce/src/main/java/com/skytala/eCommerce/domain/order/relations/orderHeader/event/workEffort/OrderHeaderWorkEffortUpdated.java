package com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;
public class OrderHeaderWorkEffortUpdated implements Event{

	private boolean success;

	public OrderHeaderWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
