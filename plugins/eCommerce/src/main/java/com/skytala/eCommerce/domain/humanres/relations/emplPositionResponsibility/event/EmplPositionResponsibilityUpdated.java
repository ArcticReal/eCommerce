package com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.model.EmplPositionResponsibility;
public class EmplPositionResponsibilityUpdated implements Event{

	private boolean success;

	public EmplPositionResponsibilityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
