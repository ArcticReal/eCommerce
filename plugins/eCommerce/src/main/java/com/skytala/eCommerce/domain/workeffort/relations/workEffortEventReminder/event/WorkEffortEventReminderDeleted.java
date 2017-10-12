package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model.WorkEffortEventReminder;
public class WorkEffortEventReminderDeleted implements Event{

	private boolean success;

	public WorkEffortEventReminderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
