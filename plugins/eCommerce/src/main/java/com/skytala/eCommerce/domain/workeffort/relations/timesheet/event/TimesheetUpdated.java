package com.skytala.eCommerce.domain.workeffort.relations.timesheet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.Timesheet;
public class TimesheetUpdated implements Event{

	private boolean success;

	public TimesheetUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
