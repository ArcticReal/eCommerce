package com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.model.WorkRequirementFulfillment;
public class WorkRequirementFulfillmentAdded implements Event{

	private WorkRequirementFulfillment addedWorkRequirementFulfillment;
	private boolean success;

	public WorkRequirementFulfillmentAdded(WorkRequirementFulfillment addedWorkRequirementFulfillment, boolean success){
		this.addedWorkRequirementFulfillment = addedWorkRequirementFulfillment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkRequirementFulfillment getAddedWorkRequirementFulfillment() {
		return addedWorkRequirementFulfillment;
	}

}
