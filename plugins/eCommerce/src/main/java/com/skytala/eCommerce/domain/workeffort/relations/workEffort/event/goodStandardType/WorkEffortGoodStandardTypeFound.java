package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandardType.WorkEffortGoodStandardType;
public class WorkEffortGoodStandardTypeFound implements Event{

	private List<WorkEffortGoodStandardType> workEffortGoodStandardTypes;

	public WorkEffortGoodStandardTypeFound(List<WorkEffortGoodStandardType> workEffortGoodStandardTypes) {
		this.workEffortGoodStandardTypes = workEffortGoodStandardTypes;
	}

	public List<WorkEffortGoodStandardType> getWorkEffortGoodStandardTypes()	{
		return workEffortGoodStandardTypes;
	}

}
