package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.attribute.WorkEffortAttribute;
public class WorkEffortAttributeAdded implements Event{

	private WorkEffortAttribute addedWorkEffortAttribute;
	private boolean success;

	public WorkEffortAttributeAdded(WorkEffortAttribute addedWorkEffortAttribute, boolean success){
		this.addedWorkEffortAttribute = addedWorkEffortAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortAttribute getAddedWorkEffortAttribute() {
		return addedWorkEffortAttribute;
	}

}
