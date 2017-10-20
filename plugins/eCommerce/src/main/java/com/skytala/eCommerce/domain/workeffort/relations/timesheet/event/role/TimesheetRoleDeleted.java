package com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.role.TimesheetRole;
public class TimesheetRoleDeleted implements Event{

	private boolean success;

	public TimesheetRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
