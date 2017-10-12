package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;
public class EmplLeaveAdded implements Event{

	private EmplLeave addedEmplLeave;
	private boolean success;

	public EmplLeaveAdded(EmplLeave addedEmplLeave, boolean success){
		this.addedEmplLeave = addedEmplLeave;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplLeave getAddedEmplLeave() {
		return addedEmplLeave;
	}

}
