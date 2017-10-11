package com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.model.WorkOrderItemFulfillment;
public class WorkOrderItemFulfillmentDeleted implements Event{

	private boolean success;

	public WorkOrderItemFulfillmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
