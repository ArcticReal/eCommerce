package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.type.WorkEffortType;
public class WorkEffortTypeUpdated implements Event{

	private boolean success;

	public WorkEffortTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
