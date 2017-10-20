package com.skytala.eCommerce.domain.order.relations.custRequest.event.requirement;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.requirement.RequirementCustRequest;
public class RequirementCustRequestFound implements Event{

	private List<RequirementCustRequest> requirementCustRequests;

	public RequirementCustRequestFound(List<RequirementCustRequest> requirementCustRequests) {
		this.requirementCustRequests = requirementCustRequests;
	}

	public List<RequirementCustRequest> getRequirementCustRequests()	{
		return requirementCustRequests;
	}

}
