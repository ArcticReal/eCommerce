package com.skytala.eCommerce.domain.order.relations.orderItem.event.workFulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.workFulfillment.WorkOrderItemFulfillment;
public class WorkOrderItemFulfillmentDeleted implements Event{

	private boolean success;

	public WorkOrderItemFulfillmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
