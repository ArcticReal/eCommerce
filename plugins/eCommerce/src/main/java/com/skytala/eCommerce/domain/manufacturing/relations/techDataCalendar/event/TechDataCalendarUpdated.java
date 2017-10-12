package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;
public class TechDataCalendarUpdated implements Event{

	private boolean success;

	public TechDataCalendarUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
