package com.skytala.eCommerce.domain.timesheet.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.timesheet.model.Timesheet;
public class TimesheetFound implements Event{

	private List<Timesheet> timesheets;

	public TimesheetFound(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public List<Timesheet> getTimesheets()	{
		return timesheets;
	}

}
