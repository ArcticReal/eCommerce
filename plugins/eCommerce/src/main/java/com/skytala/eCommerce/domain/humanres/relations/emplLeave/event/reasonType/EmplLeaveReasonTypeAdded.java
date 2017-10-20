package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.reasonType.EmplLeaveReasonType;
public class EmplLeaveReasonTypeAdded implements Event{

	private EmplLeaveReasonType addedEmplLeaveReasonType;
	private boolean success;

	public EmplLeaveReasonTypeAdded(EmplLeaveReasonType addedEmplLeaveReasonType, boolean success){
		this.addedEmplLeaveReasonType = addedEmplLeaveReasonType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplLeaveReasonType getAddedEmplLeaveReasonType() {
		return addedEmplLeaveReasonType;
	}

}
