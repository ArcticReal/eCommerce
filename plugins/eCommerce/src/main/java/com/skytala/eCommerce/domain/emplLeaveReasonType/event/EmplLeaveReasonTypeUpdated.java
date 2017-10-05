package com.skytala.eCommerce.domain.emplLeaveReasonType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.emplLeaveReasonType.model.EmplLeaveReasonType;
public class EmplLeaveReasonTypeUpdated implements Event{

	private boolean success;

	public EmplLeaveReasonTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
