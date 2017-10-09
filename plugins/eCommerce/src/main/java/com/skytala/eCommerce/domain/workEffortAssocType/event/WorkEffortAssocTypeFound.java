package com.skytala.eCommerce.domain.workEffortAssocType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortAssocType.model.WorkEffortAssocType;
public class WorkEffortAssocTypeFound implements Event{

	private List<WorkEffortAssocType> workEffortAssocTypes;

	public WorkEffortAssocTypeFound(List<WorkEffortAssocType> workEffortAssocTypes) {
		this.workEffortAssocTypes = workEffortAssocTypes;
	}

	public List<WorkEffortAssocType> getWorkEffortAssocTypes()	{
		return workEffortAssocTypes;
	}

}