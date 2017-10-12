package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.model.WorkEffortAssocAttribute;
public class WorkEffortAssocAttributeAdded implements Event{

	private WorkEffortAssocAttribute addedWorkEffortAssocAttribute;
	private boolean success;

	public WorkEffortAssocAttributeAdded(WorkEffortAssocAttribute addedWorkEffortAssocAttribute, boolean success){
		this.addedWorkEffortAssocAttribute = addedWorkEffortAssocAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortAssocAttribute getAddedWorkEffortAssocAttribute() {
		return addedWorkEffortAssocAttribute;
	}

}
