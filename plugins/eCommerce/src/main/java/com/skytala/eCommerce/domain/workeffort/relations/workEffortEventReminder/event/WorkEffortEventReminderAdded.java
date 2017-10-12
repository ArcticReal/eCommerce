package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model.WorkEffortEventReminder;
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
