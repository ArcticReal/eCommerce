package com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.model.RequirementCustRequest;
public class RequirementCustRequestFound implements Event{

	private List<RequirementCustRequest> requirementCustRequests;

	public RequirementCustRequestFound(List<RequirementCustRequest> requirementCustRequests) {
		this.requirementCustRequests = requirementCustRequests;
	}

	public List<RequirementCustRequest> getRequirementCustRequests()	{
		return requirementCustRequests;
	}

}
