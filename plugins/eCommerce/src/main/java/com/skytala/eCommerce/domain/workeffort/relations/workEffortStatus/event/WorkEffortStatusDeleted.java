package com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.model.WorkEffortStatus;
public class WorkEffortStatusDeleted implements Event{

	private boolean success;

	public WorkEffortStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
