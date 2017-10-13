package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.model.WorkEffortAssocTypeAttr;
public class WorkEffortAssocTypeAttrAdded implements Event{

	private WorkEffortAssocTypeAttr addedWorkEffortAssocTypeAttr;
	private boolean success;

	public WorkEffortAssocTypeAttrAdded(WorkEffortAssocTypeAttr addedWorkEffortAssocTypeAttr, boolean success){
		this.addedWorkEffortAssocTypeAttr = addedWorkEffortAssocTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortAssocTypeAttr getAddedWorkEffortAssocTypeAttr() {
		return addedWorkEffortAssocTypeAttr;
	}

}