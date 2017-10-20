package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.typeAttr.WorkEffortTypeAttr;
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
