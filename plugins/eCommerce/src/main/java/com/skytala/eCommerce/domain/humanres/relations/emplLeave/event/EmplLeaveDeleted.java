package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;
public class EmplLeaveDeleted implements Event{

	private boolean success;

	public EmplLeaveDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
