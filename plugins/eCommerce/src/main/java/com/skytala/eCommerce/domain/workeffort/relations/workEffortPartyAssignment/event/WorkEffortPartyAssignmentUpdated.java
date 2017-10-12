package com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.model.WorkEffortPartyAssignment;
public class WorkEffortPartyAssignmentUpdated implements Event{

	private boolean success;

	public WorkEffortPartyAssignmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
