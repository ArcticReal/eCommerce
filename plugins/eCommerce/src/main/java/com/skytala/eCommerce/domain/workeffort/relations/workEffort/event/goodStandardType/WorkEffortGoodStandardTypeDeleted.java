package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandardType.WorkEffortGoodStandardType;
public class WorkEffortGoodStandardTypeDeleted implements Event{

	private boolean success;

	public WorkEffortGoodStandardTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
