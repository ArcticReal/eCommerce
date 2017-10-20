package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandard;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandard.WorkEffortGoodStandard;
public class WorkEffortGoodStandardDeleted implements Event{

	private boolean success;

	public WorkEffortGoodStandardDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
