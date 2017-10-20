package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.type.EmplLeaveType;
public class EmplLeaveTypeFound implements Event{

	private List<EmplLeaveType> emplLeaveTypes;

	public EmplLeaveTypeFound(List<EmplLeaveType> emplLeaveTypes) {
		this.emplLeaveTypes = emplLeaveTypes;
	}

	public List<EmplLeaveType> getEmplLeaveTypes()	{
		return emplLeaveTypes;
	}

}
