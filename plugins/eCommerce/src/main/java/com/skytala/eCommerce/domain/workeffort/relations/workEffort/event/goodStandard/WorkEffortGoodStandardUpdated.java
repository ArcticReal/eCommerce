package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandard;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandard.WorkEffortGoodStandard;
public class WorkEffortGoodStandardUpdated implements Event{

	private boolean success;

	public WorkEffortGoodStandardUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
