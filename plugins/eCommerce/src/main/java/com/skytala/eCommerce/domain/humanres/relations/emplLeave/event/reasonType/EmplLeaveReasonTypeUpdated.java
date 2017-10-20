package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.reasonType.EmplLeaveReasonType;
public class EmplLeaveReasonTypeUpdated implements Event{

	private boolean success;

	public EmplLeaveReasonTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
