package com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.model.WorkEffortContactMech;
public class WorkEffortContactMechUpdated implements Event{

	private boolean success;

	public WorkEffortContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
