package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model.WorkEffortEventReminder;
public class WorkEffortEventReminderFound implements Event{

	private List<WorkEffortEventReminder> workEffortEventReminders;

	public WorkEffortEventReminderFound(List<WorkEffortEventReminder> workEffortEventReminders) {
		this.workEffortEventReminders = workEffortEventReminders;
	}

	public List<WorkEffortEventReminder> getWorkEffortEventReminders()	{
		return workEffortEventReminders;
	}

}
