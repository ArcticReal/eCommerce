package com.skytala.eCommerce.domain.workeffort.relations.workEffortType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.model.WorkEffortType;
public class WorkEffortTypeDeleted implements Event{

	private boolean success;

	public WorkEffortTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
