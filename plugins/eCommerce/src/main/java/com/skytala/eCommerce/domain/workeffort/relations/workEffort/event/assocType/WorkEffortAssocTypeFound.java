package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocType.WorkEffortAssocType;
public class WorkEffortAssocTypeFound implements Event{

	private List<WorkEffortAssocType> workEffortAssocTypes;

	public WorkEffortAssocTypeFound(List<WorkEffortAssocType> workEffortAssocTypes) {
		this.workEffortAssocTypes = workEffortAssocTypes;
	}

	public List<WorkEffortAssocType> getWorkEffortAssocTypes()	{
		return workEffortAssocTypes;
	}

}
