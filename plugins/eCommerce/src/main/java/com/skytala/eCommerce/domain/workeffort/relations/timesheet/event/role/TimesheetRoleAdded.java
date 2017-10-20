package com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.role.TimesheetRole;
public class TimesheetRoleAdded implements Event{

	private TimesheetRole addedTimesheetRole;
	private boolean success;

	public TimesheetRoleAdded(TimesheetRole addedTimesheetRole, boolean success){
		this.addedTimesheetRole = addedTimesheetRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TimesheetRole getAddedTimesheetRole() {
		return addedTimesheetRole;
	}

}
