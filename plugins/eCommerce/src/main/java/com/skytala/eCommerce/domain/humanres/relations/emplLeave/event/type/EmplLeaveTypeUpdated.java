package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.type.EmplLeaveType;
public class EmplLeaveTypeUpdated implements Event{

	private boolean success;

	public EmplLeaveTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
