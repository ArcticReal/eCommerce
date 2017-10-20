package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.purposeType.WorkEffortPurposeType;
public class WorkEffortPurposeTypeAdded implements Event{

	private WorkEffortPurposeType addedWorkEffortPurposeType;
	private boolean success;

	public WorkEffortPurposeTypeAdded(WorkEffortPurposeType addedWorkEffortPurposeType, boolean success){
		this.addedWorkEffortPurposeType = addedWorkEffortPurposeType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortPurposeType getAddedWorkEffortPurposeType() {
		return addedWorkEffortPurposeType;
	}

}
