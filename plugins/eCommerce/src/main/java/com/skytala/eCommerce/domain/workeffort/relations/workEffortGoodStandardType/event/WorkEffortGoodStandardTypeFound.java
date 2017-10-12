package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.model.WorkEffortGoodStandardType;
public class WorkEffortGoodStandardTypeFound implements Event{

	private List<WorkEffortGoodStandardType> workEffortGoodStandardTypes;

	public WorkEffortGoodStandardTypeFound(List<WorkEffortGoodStandardType> workEffortGoodStandardTypes) {
		this.workEffortGoodStandardTypes = workEffortGoodStandardTypes;
	}

	public List<WorkEffortGoodStandardType> getWorkEffortGoodStandardTypes()	{
		return workEffortGoodStandardTypes;
	}

}
