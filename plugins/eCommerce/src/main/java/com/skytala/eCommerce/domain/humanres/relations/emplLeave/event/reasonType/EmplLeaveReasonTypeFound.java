package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.reasonType.EmplLeaveReasonType;
public class EmplLeaveReasonTypeFound implements Event{

	private List<EmplLeaveReasonType> emplLeaveReasonTypes;

	public EmplLeaveReasonTypeFound(List<EmplLeaveReasonType> emplLeaveReasonTypes) {
		this.emplLeaveReasonTypes = emplLeaveReasonTypes;
	}

	public List<EmplLeaveReasonType> getEmplLeaveReasonTypes()	{
		return emplLeaveReasonTypes;
	}

}
