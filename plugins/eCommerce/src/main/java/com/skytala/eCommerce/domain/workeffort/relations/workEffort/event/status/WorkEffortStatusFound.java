package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;
public class WorkEffortStatusFound implements Event{

	private List<WorkEffortStatus> workEffortStatuss;

	public WorkEffortStatusFound(List<WorkEffortStatus> workEffortStatuss) {
		this.workEffortStatuss = workEffortStatuss;
	}

	public List<WorkEffortStatus> getWorkEffortStatuss()	{
		return workEffortStatuss;
	}

}
