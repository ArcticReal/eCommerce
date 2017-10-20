package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.partyAssignment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.partyAssignment.WorkEffortPartyAssignment;
public class WorkEffortPartyAssignmentUpdated implements Event{

	private boolean success;

	public WorkEffortPartyAssignmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
