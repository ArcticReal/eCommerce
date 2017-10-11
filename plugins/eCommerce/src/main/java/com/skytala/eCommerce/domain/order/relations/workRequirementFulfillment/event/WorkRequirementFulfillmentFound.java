package com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.model.WorkRequirementFulfillment;
public class WorkRequirementFulfillmentFound implements Event{

	private List<WorkRequirementFulfillment> workRequirementFulfillments;

	public WorkRequirementFulfillmentFound(List<WorkRequirementFulfillment> workRequirementFulfillments) {
		this.workRequirementFulfillments = workRequirementFulfillments;
	}

	public List<WorkRequirementFulfillment> getWorkRequirementFulfillments()	{
		return workRequirementFulfillments;
	}

}
