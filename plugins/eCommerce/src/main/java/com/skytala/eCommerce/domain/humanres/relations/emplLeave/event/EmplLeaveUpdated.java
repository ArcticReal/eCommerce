package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;
public class EmplLeaveUpdated implements Event{

	private boolean success;

	public EmplLeaveUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
