package com.skytala.eCommerce.domain.workEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffort.model.WorkEffort;
public class WorkEffortUpdated implements Event{

	private boolean success;

	public WorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
