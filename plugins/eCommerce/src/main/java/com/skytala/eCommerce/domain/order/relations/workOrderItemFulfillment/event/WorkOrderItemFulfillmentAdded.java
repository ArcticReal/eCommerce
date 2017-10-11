package com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.model.WorkOrderItemFulfillment;
public class WorkOrderItemFulfillmentAdded implements Event{

	private WorkOrderItemFulfillment addedWorkOrderItemFulfillment;
	private boolean success;

	public WorkOrderItemFulfillmentAdded(WorkOrderItemFulfillment addedWorkOrderItemFulfillment, boolean success){
		this.addedWorkOrderItemFulfillment = addedWorkOrderItemFulfillment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkOrderItemFulfillment getAddedWorkOrderItemFulfillment() {
		return addedWorkOrderItemFulfillment;
	}

}
