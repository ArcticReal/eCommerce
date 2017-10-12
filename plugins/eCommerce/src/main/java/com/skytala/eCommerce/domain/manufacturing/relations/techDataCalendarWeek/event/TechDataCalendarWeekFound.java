package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarWeek.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarWeek.model.TechDataCalendarWeek;
public class TechDataCalendarWeekFound implements Event{

	private List<TechDataCalendarWeek> techDataCalendarWeeks;

	public TechDataCalendarWeekFound(List<TechDataCalendarWeek> techDataCalendarWeeks) {
		this.techDataCalendarWeeks = techDataCalendarWeeks;
	}

	public List<TechDataCalendarWeek> getTechDataCalendarWeeks()	{
		return techDataCalendarWeeks;
	}

}
