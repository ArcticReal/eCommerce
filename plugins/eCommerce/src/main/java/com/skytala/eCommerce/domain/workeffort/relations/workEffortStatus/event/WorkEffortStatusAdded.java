package com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.model.WorkEffortStatus;
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
