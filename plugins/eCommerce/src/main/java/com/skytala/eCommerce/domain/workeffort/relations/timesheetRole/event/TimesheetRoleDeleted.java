package com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.model.TimesheetRole;
public class TimesheetRoleDeleted implements Event{

	private boolean success;

	public TimesheetRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
