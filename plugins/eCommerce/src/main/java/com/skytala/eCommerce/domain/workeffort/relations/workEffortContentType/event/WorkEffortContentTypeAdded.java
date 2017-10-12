package com.skytala.eCommerce.domain.workeffort.relations.workEffortContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContentType.model.WorkEffortContentType;
public class WorkEffortContentTypeAdded implements Event{

	private WorkEffortContentType addedWorkEffortContentType;
	private boolean success;

	public WorkEffortContentTypeAdded(WorkEffortContentType addedWorkEffortContentType, boolean success){
		this.addedWorkEffortContentType = addedWorkEffortContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortContentType getAddedWorkEffortContentType() {
		return addedWorkEffortContentType;
	}

}
