package com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.role.TimesheetRole;
public class TimesheetRoleFound implements Event{

	private List<TimesheetRole> timesheetRoles;

	public TimesheetRoleFound(List<TimesheetRole> timesheetRoles) {
		this.timesheetRoles = timesheetRoles;
	}

	public List<TimesheetRole> getTimesheetRoles()	{
		return timesheetRoles;
	}

}
