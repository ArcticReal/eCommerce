package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;
public class TechDataCalendarAdded implements Event{

	private TechDataCalendar addedTechDataCalendar;
	private boolean success;

	public TechDataCalendarAdded(TechDataCalendar addedTechDataCalendar, boolean success){
		this.addedTechDataCalendar = addedTechDataCalendar;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TechDataCalendar getAddedTechDataCalendar() {
		return addedTechDataCalendar;
	}

}
