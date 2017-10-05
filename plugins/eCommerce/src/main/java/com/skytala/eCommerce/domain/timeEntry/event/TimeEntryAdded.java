package com.skytala.eCommerce.domain.timeEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.timeEntry.model.TimeEntry;
public class TimeEntryAdded implements Event{

	private TimeEntry addedTimeEntry;
	private boolean success;

	public TimeEntryAdded(TimeEntry addedTimeEntry, boolean success){
		this.addedTimeEntry = addedTimeEntry;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TimeEntry getAddedTimeEntry() {
		return addedTimeEntry;
	}

}
