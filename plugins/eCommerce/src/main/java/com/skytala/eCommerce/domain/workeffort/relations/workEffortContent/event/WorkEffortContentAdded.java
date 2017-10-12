package com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.model.WorkEffortContent;
public class WorkEffortContentAdded implements Event{

	private WorkEffortContent addedWorkEffortContent;
	private boolean success;

	public WorkEffortContentAdded(WorkEffortContent addedWorkEffortContent, boolean success){
		this.addedWorkEffortContent = addedWorkEffortContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortContent getAddedWorkEffortContent() {
		return addedWorkEffortContent;
	}

}
