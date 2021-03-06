package com.skytala.eCommerce.domain.order.relations.orderItem.event.workFulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.workFulfillment.WorkOrderItemFulfillment;
public class WorkOrderItemFulfillmentUpdated implements Event{

	private boolean success;

	public WorkOrderItemFulfillmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
