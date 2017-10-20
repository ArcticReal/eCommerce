package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.type.WorkEffortType;
public class WorkEffortTypeDeleted implements Event{

	private boolean success;

	public WorkEffortTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
