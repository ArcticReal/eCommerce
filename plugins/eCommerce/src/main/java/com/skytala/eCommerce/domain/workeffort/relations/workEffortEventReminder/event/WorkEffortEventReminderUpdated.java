package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model.WorkEffortEventReminder;
public class WorkEffortEventReminderUpdated implements Event{

	private boolean success;

	public WorkEffortEventReminderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
