package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;
public class WorkEffortContactMechUpdated implements Event{

	private boolean success;

	public WorkEffortContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
