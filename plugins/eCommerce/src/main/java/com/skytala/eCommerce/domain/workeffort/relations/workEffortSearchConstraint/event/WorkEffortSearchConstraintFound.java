package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.model.WorkEffortSearchConstraint;
public class WorkEffortSearchConstraintFound implements Event{

	private List<WorkEffortSearchConstraint> workEffortSearchConstraints;

	public WorkEffortSearchConstraintFound(List<WorkEffortSearchConstraint> workEffortSearchConstraints) {
		this.workEffortSearchConstraints = workEffortSearchConstraints;
	}

	public List<WorkEffortSearchConstraint> getWorkEffortSearchConstraints()	{
		return workEffortSearchConstraints;
	}

}
