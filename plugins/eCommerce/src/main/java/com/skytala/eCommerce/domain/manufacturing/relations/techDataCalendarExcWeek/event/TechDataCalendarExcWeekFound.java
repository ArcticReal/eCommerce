package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model.TechDataCalendarExcWeek;
public class TechDataCalendarExcWeekFound implements Event{

	private List<TechDataCalendarExcWeek> techDataCalendarExcWeeks;

	public TechDataCalendarExcWeekFound(List<TechDataCalendarExcWeek> techDataCalendarExcWeeks) {
		this.techDataCalendarExcWeeks = techDataCalendarExcWeeks;
	}

	public List<TechDataCalendarExcWeek> getTechDataCalendarExcWeeks()	{
		return techDataCalendarExcWeeks;
	}

}
