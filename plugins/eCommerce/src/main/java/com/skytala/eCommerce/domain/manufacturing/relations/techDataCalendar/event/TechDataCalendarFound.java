package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;
public class TechDataCalendarFound implements Event{

	private List<TechDataCalendar> techDataCalendars;

	public TechDataCalendarFound(List<TechDataCalendar> techDataCalendars) {
		this.techDataCalendars = techDataCalendars;
	}

	public List<TechDataCalendar> getTechDataCalendars()	{
		return techDataCalendars;
	}

}
