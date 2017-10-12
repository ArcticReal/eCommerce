package com.skytala.eCommerce.domain.humanres.relations.emplLeaveReasonType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeaveReasonType.model.EmplLeaveReasonType;
public class EmplLeaveReasonTypeDeleted implements Event{

	private boolean success;

	public EmplLeaveReasonTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
