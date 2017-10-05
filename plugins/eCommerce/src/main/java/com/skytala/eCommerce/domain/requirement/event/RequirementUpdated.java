package com.skytala.eCommerce.domain.requirement.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.requirement.model.Requirement;
public class RequirementUpdated implements Event{

	private boolean success;

	public RequirementUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
