package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.model.TechDataCalendarExcDay;
public class TechDataCalendarExcDayAdded implements Event{

	private TechDataCalendarExcDay addedTechDataCalendarExcDay;
	private boolean success;

	public TechDataCalendarExcDayAdded(TechDataCalendarExcDay addedTechDataCalendarExcDay, boolean success){
		this.addedTechDataCalendarExcDay = addedTechDataCalendarExcDay;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TechDataCalendarExcDay getAddedTechDataCalendarExcDay() {
		return addedTechDataCalendarExcDay;
	}

}
