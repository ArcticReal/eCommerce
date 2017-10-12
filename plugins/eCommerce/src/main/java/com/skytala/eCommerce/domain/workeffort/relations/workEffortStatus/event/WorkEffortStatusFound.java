package com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.model.WorkEffortStatus;
public class WorkEffortStatusFound implements Event{

	private List<WorkEffortStatus> workEffortStatuss;

	public WorkEffortStatusFound(List<WorkEffortStatus> workEffortStatuss) {
		this.workEffortStatuss = workEffortStatuss;
	}

	public List<WorkEffortStatus> getWorkEffortStatuss()	{
		return workEffortStatuss;
	}

}
