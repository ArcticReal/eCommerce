package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;
public class TechDataCalendarDeleted implements Event{

	private boolean success;

	public TechDataCalendarDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
