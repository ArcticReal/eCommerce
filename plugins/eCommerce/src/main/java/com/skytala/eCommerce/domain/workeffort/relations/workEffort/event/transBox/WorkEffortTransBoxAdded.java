package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;
public class WorkEffortTransBoxAdded implements Event{

	private WorkEffortTransBox addedWorkEffortTransBox;
	private boolean success;

	public WorkEffortTransBoxAdded(WorkEffortTransBox addedWorkEffortTransBox, boolean success){
		this.addedWorkEffortTransBox = addedWorkEffortTransBox;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortTransBox getAddedWorkEffortTransBox() {
		return addedWorkEffortTransBox;
	}

}
