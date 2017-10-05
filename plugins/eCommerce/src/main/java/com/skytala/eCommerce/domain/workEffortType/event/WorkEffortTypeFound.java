package com.skytala.eCommerce.domain.workEffortType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortType.model.WorkEffortType;
public class WorkEffortTypeFound implements Event{

	private List<WorkEffortType> workEffortTypes;

	public WorkEffortTypeFound(List<WorkEffortType> workEffortTypes) {
		this.workEffortTypes = workEffortTypes;
	}

	public List<WorkEffortType> getWorkEffortTypes()	{
		return workEffortTypes;
	}

}
