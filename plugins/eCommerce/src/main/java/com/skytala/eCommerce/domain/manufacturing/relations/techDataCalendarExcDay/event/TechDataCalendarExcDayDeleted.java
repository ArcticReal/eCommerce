package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.model.TechDataCalendarExcDay;
public class TechDataCalendarExcDayDeleted implements Event{

	private boolean success;

	public TechDataCalendarExcDayDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
