package com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.model.WorkOrderItemFulfillment;
public class WorkOrderItemFulfillmentFound implements Event{

	private List<WorkOrderItemFulfillment> workOrderItemFulfillments;

	public WorkOrderItemFulfillmentFound(List<WorkOrderItemFulfillment> workOrderItemFulfillments) {
		this.workOrderItemFulfillments = workOrderItemFulfillments;
	}

	public List<WorkOrderItemFulfillment> getWorkOrderItemFulfillments()	{
		return workOrderItemFulfillments;
	}

}
