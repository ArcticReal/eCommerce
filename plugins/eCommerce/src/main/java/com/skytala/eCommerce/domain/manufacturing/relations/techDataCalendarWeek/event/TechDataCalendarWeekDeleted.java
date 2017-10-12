package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarWeek.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarWeek.model.TechDataCalendarWeek;
public class TechDataCalendarWeekDeleted implements Event{

	private boolean success;

	public TechDataCalendarWeekDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
