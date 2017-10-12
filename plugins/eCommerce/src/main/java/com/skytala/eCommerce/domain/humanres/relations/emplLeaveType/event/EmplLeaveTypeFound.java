package com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.model.EmplLeaveType;
public class EmplLeaveTypeFound implements Event{

	private List<EmplLeaveType> emplLeaveTypes;

	public EmplLeaveTypeFound(List<EmplLeaveType> emplLeaveTypes) {
		this.emplLeaveTypes = emplLeaveTypes;
	}

	public List<EmplLeaveType> getEmplLeaveTypes()	{
		return emplLeaveTypes;
	}

}
