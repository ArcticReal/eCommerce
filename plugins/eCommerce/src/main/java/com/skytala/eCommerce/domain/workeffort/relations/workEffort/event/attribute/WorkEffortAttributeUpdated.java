package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.attribute.WorkEffortAttribute;
public class WorkEffortAttributeUpdated implements Event{

	private boolean success;

	public WorkEffortAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
