package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;
public class WorkEffortStatusDeleted implements Event{

	private boolean success;

	public WorkEffortStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
