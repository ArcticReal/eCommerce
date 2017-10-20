package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchConstraint.WorkEffortSearchConstraint;
public class WorkEffortSearchConstraintUpdated implements Event{

	private boolean success;

	public WorkEffortSearchConstraintUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
