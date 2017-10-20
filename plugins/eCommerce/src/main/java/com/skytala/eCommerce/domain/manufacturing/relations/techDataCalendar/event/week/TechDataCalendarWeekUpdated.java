package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week.TechDataCalendarWeek;
public class TechDataCalendarWeekUpdated implements Event{

	private boolean success;

	public TechDataCalendarWeekUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
