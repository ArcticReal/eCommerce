package com.skytala.eCommerce.domain.timesheet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.timesheet.model.Timesheet;
public class TimesheetAdded implements Event{

	private Timesheet addedTimesheet;
	private boolean success;

	public TimesheetAdded(Timesheet addedTimesheet, boolean success){
		this.addedTimesheet = addedTimesheet;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Timesheet getAddedTimesheet() {
		return addedTimesheet;
	}

}
