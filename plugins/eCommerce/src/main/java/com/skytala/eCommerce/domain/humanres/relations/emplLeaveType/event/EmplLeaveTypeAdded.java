package com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.model.EmplLeaveType;
public class EmplLeaveTypeAdded implements Event{

	private EmplLeaveType addedEmplLeaveType;
	private boolean success;

	public EmplLeaveTypeAdded(EmplLeaveType addedEmplLeaveType, boolean success){
		this.addedEmplLeaveType = addedEmplLeaveType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplLeaveType getAddedEmplLeaveType() {
		return addedEmplLeaveType;
	}

}
