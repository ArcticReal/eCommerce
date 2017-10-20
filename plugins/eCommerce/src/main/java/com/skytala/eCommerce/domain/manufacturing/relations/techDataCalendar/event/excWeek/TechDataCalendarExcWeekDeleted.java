package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excWeek.TechDataCalendarExcWeek;
public class TechDataCalendarExcWeekDeleted implements Event{

	private boolean success;

	public TechDataCalendarExcWeekDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
