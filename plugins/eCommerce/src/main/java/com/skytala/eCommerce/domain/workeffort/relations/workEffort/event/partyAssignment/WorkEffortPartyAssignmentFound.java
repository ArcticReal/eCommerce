package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.partyAssignment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.partyAssignment.WorkEffortPartyAssignment;
public class WorkEffortPartyAssignmentFound implements Event{

	private List<WorkEffortPartyAssignment> workEffortPartyAssignments;

	public WorkEffortPartyAssignmentFound(List<WorkEffortPartyAssignment> workEffortPartyAssignments) {
		this.workEffortPartyAssignments = workEffortPartyAssignments;
	}

	public List<WorkEffortPartyAssignment> getWorkEffortPartyAssignments()	{
		return workEffortPartyAssignments;
	}

}
