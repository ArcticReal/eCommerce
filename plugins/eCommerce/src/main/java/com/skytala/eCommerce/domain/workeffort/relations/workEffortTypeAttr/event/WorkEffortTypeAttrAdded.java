package com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.model.WorkEffortTypeAttr;
public class WorkEffortTypeAttrAdded implements Event{

	private WorkEffortTypeAttr addedWorkEffortTypeAttr;
	private boolean success;

	public WorkEffortTypeAttrAdded(WorkEffortTypeAttr addedWorkEffortTypeAttr, boolean success){
		this.addedWorkEffortTypeAttr = addedWorkEffortTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortTypeAttr getAddedWorkEffortTypeAttr() {
		return addedWorkEffortTypeAttr;
	}

}
