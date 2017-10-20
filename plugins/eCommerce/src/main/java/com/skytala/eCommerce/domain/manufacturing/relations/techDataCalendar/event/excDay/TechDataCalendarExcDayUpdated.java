package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excDay.TechDataCalendarExcDay;
public class TechDataCalendarExcDayUpdated implements Event{

	private boolean success;

	public TechDataCalendarExcDayUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
