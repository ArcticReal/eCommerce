package com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.model.WorkEffortPartyAssignment;
public class WorkEffortPartyAssignmentAdded implements Event{

	private WorkEffortPartyAssignment addedWorkEffortPartyAssignment;
	private boolean success;

	public WorkEffortPartyAssignmentAdded(WorkEffortPartyAssignment addedWorkEffortPartyAssignment, boolean success){
		this.addedWorkEffortPartyAssignment = addedWorkEffortPartyAssignment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortPartyAssignment getAddedWorkEffortPartyAssignment() {
		return addedWorkEffortPartyAssignment;
	}

}
