package com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.model.RequirementCustRequest;
public class RequirementCustRequestAdded implements Event{

	private RequirementCustRequest addedRequirementCustRequest;
	private boolean success;

	public RequirementCustRequestAdded(RequirementCustRequest addedRequirementCustRequest, boolean success){
		this.addedRequirementCustRequest = addedRequirementCustRequest;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RequirementCustRequest getAddedRequirementCustRequest() {
		return addedRequirementCustRequest;
	}

}
