package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model.TechDataCalendarExcWeek;
public class TechDataCalendarExcWeekUpdated implements Event{

	private boolean success;

	public TechDataCalendarExcWeekUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
