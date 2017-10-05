package com.skytala.eCommerce.domain.timesheet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.timesheet.model.Timesheet;
public class TimesheetDeleted implements Event{

	private boolean success;

	public TimesheetDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
