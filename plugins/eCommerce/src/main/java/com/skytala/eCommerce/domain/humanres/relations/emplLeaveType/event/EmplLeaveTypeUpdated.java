package com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.model.EmplLeaveType;
public class EmplLeaveTypeUpdated implements Event{

	private boolean success;

	public EmplLeaveTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
