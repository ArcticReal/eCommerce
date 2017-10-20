package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandardType.WorkEffortGoodStandardType;
public class WorkEffortGoodStandardTypeUpdated implements Event{

	private boolean success;

	public WorkEffortGoodStandardTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
