package com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.model.EmplPositionResponsibility;
public class EmplPositionResponsibilityDeleted implements Event{

	private boolean success;

	public EmplPositionResponsibilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
