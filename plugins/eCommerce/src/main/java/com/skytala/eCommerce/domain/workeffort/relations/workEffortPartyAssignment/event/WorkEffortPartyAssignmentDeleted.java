package com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.model.WorkEffortPartyAssignment;
public class WorkEffortPartyAssignmentDeleted implements Event{

	private boolean success;

	public WorkEffortPartyAssignmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
