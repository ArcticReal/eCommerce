package com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.model.WorkEffortContactMech;
public class WorkEffortContactMechDeleted implements Event{

	private boolean success;

	public WorkEffortContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
