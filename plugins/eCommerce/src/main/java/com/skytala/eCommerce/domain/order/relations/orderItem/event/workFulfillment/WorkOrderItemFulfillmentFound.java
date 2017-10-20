package com.skytala.eCommerce.domain.order.relations.orderItem.event.workFulfillment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.workFulfillment.WorkOrderItemFulfillment;
public class WorkOrderItemFulfillmentFound implements Event{

	private List<WorkOrderItemFulfillment> workOrderItemFulfillments;

	public WorkOrderItemFulfillmentFound(List<WorkOrderItemFulfillment> workOrderItemFulfillments) {
		this.workOrderItemFulfillments = workOrderItemFulfillments;
	}

	public List<WorkOrderItemFulfillment> getWorkOrderItemFulfillments()	{
		return workOrderItemFulfillments;
	}

}
