package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocAttribute.WorkEffortAssocAttribute;
public class WorkEffortAssocAttributeUpdated implements Event{

	private boolean success;

	public WorkEffortAssocAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
