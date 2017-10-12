package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.model.WorkEffortGoodStandardType;
public class WorkEffortGoodStandardTypeUpdated implements Event{

	private boolean success;

	public WorkEffortGoodStandardTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
