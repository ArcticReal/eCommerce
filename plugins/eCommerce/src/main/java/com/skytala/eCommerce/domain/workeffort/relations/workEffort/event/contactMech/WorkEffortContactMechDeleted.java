package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;
public class WorkEffortContactMechDeleted implements Event{

	private boolean success;

	public WorkEffortContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
