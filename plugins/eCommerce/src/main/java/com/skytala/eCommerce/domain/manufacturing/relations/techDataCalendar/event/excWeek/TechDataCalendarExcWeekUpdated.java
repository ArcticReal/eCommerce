package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excWeek.TechDataCalendarExcWeek;
public class TechDataCalendarExcWeekUpdated implements Event{

	private boolean success;

	public TechDataCalendarExcWeekUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
