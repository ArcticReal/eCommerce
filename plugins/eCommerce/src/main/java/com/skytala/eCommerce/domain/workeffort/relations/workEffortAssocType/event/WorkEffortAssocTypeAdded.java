package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.model.WorkEffortAssocType;
public class WorkEffortAssocTypeAdded implements Event{

	private WorkEffortAssocType addedWorkEffortAssocType;
	private boolean success;

	public WorkEffortAssocTypeAdded(WorkEffortAssocType addedWorkEffortAssocType, boolean success){
		this.addedWorkEffortAssocType = addedWorkEffortAssocType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortAssocType getAddedWorkEffortAssocType() {
		return addedWorkEffortAssocType;
	}

}
