package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excDay.TechDataCalendarExcDay;
public class TechDataCalendarExcDayFound implements Event{

	private List<TechDataCalendarExcDay> techDataCalendarExcDays;

	public TechDataCalendarExcDayFound(List<TechDataCalendarExcDay> techDataCalendarExcDays) {
		this.techDataCalendarExcDays = techDataCalendarExcDays;
	}

	public List<TechDataCalendarExcDay> getTechDataCalendarExcDays()	{
		return techDataCalendarExcDays;
	}

}
