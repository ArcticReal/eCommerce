package com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.model.WorkEffortAttribute;
public class WorkEffortAttributeDeleted implements Event{

	private boolean success;

	public WorkEffortAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
