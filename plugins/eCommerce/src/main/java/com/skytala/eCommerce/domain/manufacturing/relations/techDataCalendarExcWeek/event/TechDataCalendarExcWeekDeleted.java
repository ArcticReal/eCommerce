package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model.TechDataCalendarExcWeek;
public class TechDataCalendarExcWeekDeleted implements Event{

	private boolean success;

	public TechDataCalendarExcWeekDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
