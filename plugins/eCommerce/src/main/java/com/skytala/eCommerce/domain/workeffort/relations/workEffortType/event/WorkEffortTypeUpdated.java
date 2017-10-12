package com.skytala.eCommerce.domain.workeffort.relations.workEffortType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.model.WorkEffortType;
public class WorkEffortTypeUpdated implements Event{

	private boolean success;

	public WorkEffortTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
