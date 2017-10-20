package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocType.WorkEffortAssocType;
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
