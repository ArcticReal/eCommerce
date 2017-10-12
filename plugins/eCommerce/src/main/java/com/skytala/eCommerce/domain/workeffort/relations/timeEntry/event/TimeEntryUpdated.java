package com.skytala.eCommerce.domain.workeffort.relations.timeEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.model.TimeEntry;
public class TimeEntryUpdated implements Event{

	private boolean success;

	public TimeEntryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
