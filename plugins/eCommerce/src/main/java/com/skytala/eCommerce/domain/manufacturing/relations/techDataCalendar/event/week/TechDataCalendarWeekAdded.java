package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week.TechDataCalendarWeek;
public class TechDataCalendarWeekAdded implements Event{

	private TechDataCalendarWeek addedTechDataCalendarWeek;
	private boolean success;

	public TechDataCalendarWeekAdded(TechDataCalendarWeek addedTechDataCalendarWeek, boolean success){
		this.addedTechDataCalendarWeek = addedTechDataCalendarWeek;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TechDataCalendarWeek getAddedTechDataCalendarWeek() {
		return addedTechDataCalendarWeek;
	}

}
