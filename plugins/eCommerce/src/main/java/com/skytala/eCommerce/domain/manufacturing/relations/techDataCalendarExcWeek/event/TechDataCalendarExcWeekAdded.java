package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model.TechDataCalendarExcWeek;
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
