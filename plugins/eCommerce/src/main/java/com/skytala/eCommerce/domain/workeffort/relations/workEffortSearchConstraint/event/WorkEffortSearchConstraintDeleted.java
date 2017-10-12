package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.model.WorkEffortSearchConstraint;
public class WorkEffortSearchConstraintDeleted implements Event{

	private boolean success;

	public WorkEffortSearchConstraintDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
