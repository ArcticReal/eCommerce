package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.eventReminder.WorkEffortEventReminder;
public class WorkEffortEventReminderDeleted implements Event{

	private boolean success;

	public WorkEffortEventReminderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
