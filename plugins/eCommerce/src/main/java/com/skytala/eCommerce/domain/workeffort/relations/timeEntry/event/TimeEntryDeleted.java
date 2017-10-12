package com.skytala.eCommerce.domain.workeffort.relations.timeEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.model.TimeEntry;
public class TimeEntryDeleted implements Event{

	private boolean success;

	public TimeEntryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
