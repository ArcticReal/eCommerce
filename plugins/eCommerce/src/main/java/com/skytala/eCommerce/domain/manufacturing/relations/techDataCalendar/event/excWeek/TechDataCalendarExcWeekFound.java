package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excWeek.TechDataCalendarExcWeek;
public class TechDataCalendarExcWeekFound implements Event{

	private List<TechDataCalendarExcWeek> techDataCalendarExcWeeks;

	public TechDataCalendarExcWeekFound(List<TechDataCalendarExcWeek> techDataCalendarExcWeeks) {
		this.techDataCalendarExcWeeks = techDataCalendarExcWeeks;
	}

	public List<TechDataCalendarExcWeek> getTechDataCalendarExcWeeks()	{
		return techDataCalendarExcWeeks;
	}

}
