package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.model.WorkEffortSearchConstraint;
public class WorkEffortSearchConstraintAdded implements Event{

	private WorkEffortSearchConstraint addedWorkEffortSearchConstraint;
	private boolean success;

	public WorkEffortSearchConstraintAdded(WorkEffortSearchConstraint addedWorkEffortSearchConstraint, boolean success){
		this.addedWorkEffortSearchConstraint = addedWorkEffortSearchConstraint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortSearchConstraint getAddedWorkEffortSearchConstraint() {
		return addedWorkEffortSearchConstraint;
	}

}
