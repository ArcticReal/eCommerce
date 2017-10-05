package com.skytala.eCommerce.domain.requirementType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.requirementType.model.RequirementType;
public class RequirementTypeUpdated implements Event{

	private boolean success;

	public RequirementTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
