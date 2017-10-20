package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.eventReminder.WorkEffortEventReminder;
public class WorkEffortEventReminderFound implements Event{

	private List<WorkEffortEventReminder> workEffortEventReminders;

	public WorkEffortEventReminderFound(List<WorkEffortEventReminder> workEffortEventReminders) {
		this.workEffortEventReminders = workEffortEventReminders;
	}

	public List<WorkEffortEventReminder> getWorkEffortEventReminders()	{
		return workEffortEventReminders;
	}

}
