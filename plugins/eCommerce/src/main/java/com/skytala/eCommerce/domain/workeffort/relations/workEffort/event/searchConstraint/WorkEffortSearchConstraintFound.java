package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchConstraint.WorkEffortSearchConstraint;
public class WorkEffortSearchConstraintFound implements Event{

	private List<WorkEffortSearchConstraint> workEffortSearchConstraints;

	public WorkEffortSearchConstraintFound(List<WorkEffortSearchConstraint> workEffortSearchConstraints) {
		this.workEffortSearchConstraints = workEffortSearchConstraints;
	}

	public List<WorkEffortSearchConstraint> getWorkEffortSearchConstraints()	{
		return workEffortSearchConstraints;
	}

}
