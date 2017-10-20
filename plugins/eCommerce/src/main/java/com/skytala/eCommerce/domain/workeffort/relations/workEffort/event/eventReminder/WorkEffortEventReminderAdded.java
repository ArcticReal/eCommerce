package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.eventReminder.WorkEffortEventReminder;
public class WorkEffortEventReminderAdded implements Event{

	private WorkEffortEventReminder addedWorkEffortEventReminder;
	private boolean success;

	public WorkEffortEventReminderAdded(WorkEffortEventReminder addedWorkEffortEventReminder, boolean success){
		this.addedWorkEffortEventReminder = addedWorkEffortEventReminder;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortEventReminder getAddedWorkEffortEventReminder() {
		return addedWorkEffortEventReminder;
	}

}
