package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.WorkEffort;
public class WorkEffortFound implements Event{

	private List<WorkEffort> workEfforts;

	public WorkEffortFound(List<WorkEffort> workEfforts) {
		this.workEfforts = workEfforts;
	}

	public List<WorkEffort> getWorkEfforts()	{
		return workEfforts;
	}

}
