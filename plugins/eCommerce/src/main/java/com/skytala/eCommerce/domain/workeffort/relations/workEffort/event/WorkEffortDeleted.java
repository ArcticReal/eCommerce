package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.WorkEffort;
public class WorkEffortDeleted implements Event{

	private boolean success;

	public WorkEffortDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
