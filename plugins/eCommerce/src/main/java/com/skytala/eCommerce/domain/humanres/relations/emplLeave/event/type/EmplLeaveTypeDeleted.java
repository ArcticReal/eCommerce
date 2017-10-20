package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.type.EmplLeaveType;
public class EmplLeaveTypeDeleted implements Event{

	private boolean success;

	public EmplLeaveTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
