package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excWeek.TechDataCalendarExcWeek;
public class TechDataCalendarExcWeekAdded implements Event{

	private TechDataCalendarExcWeek addedTechDataCalendarExcWeek;
	private boolean success;

	public TechDataCalendarExcWeekAdded(TechDataCalendarExcWeek addedTechDataCalendarExcWeek, boolean success){
		this.addedTechDataCalendarExcWeek = addedTechDataCalendarExcWeek;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TechDataCalendarExcWeek getAddedTechDataCalendarExcWeek() {
		return addedTechDataCalendarExcWeek;
	}

}
