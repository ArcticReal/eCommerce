package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;
public class WorkEffortStatusAdded implements Event{

	private WorkEffortStatus addedWorkEffortStatus;
	private boolean success;

	public WorkEffortStatusAdded(WorkEffortStatus addedWorkEffortStatus, boolean success){
		this.addedWorkEffortStatus = addedWorkEffortStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortStatus getAddedWorkEffortStatus() {
		return addedWorkEffortStatus;
	}

}
