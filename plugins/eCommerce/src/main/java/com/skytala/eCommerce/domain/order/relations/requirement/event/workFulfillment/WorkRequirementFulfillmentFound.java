package com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.workFulfillment.WorkRequirementFulfillment;
public class WorkRequirementFulfillmentFound implements Event{

	private List<WorkRequirementFulfillment> workRequirementFulfillments;

	public WorkRequirementFulfillmentFound(List<WorkRequirementFulfillment> workRequirementFulfillments) {
		this.workRequirementFulfillments = workRequirementFulfillments;
	}

	public List<WorkRequirementFulfillment> getWorkRequirementFulfillments()	{
		return workRequirementFulfillments;
	}

}
