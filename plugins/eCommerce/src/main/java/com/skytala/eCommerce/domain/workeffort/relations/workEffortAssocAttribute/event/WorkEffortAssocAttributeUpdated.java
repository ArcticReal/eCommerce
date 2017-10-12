package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.model.WorkEffortAssocAttribute;
public class WorkEffortAssocAttributeUpdated implements Event{

	private boolean success;

	public WorkEffortAssocAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
