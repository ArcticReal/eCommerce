package com.skytala.eCommerce.domain.workeffort.relations.timeEntry.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.model.TimeEntry;
public class TimeEntryFound implements Event{

	private List<TimeEntry> timeEntrys;

	public TimeEntryFound(List<TimeEntry> timeEntrys) {
		this.timeEntrys = timeEntrys;
	}

	public List<TimeEntry> getTimeEntrys()	{
		return timeEntrys;
	}

}
