package com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.model.TimesheetRole;
public class TimesheetRoleUpdated implements Event{

	private boolean success;

	public TimesheetRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
