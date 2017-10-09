package com.skytala.eCommerce.domain.timeEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.timeEntry.model.TimeEntry;
public class TimeEntryDeleted implements Event{

	private boolean success;

	public TimeEntryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}