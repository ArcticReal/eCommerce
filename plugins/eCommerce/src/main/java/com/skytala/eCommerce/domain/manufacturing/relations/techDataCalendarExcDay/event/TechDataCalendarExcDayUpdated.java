package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.model.TechDataCalendarExcDay;
public class TechDataCalendarExcDayUpdated implements Event{

	private boolean success;

	public TechDataCalendarExcDayUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
