package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarWeek.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarWeek.model.TechDataCalendarWeek;
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
