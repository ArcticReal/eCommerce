package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assoc.WorkEffortAssoc;
public class WorkEffortAssocUpdated implements Event{

	private boolean success;

	public WorkEffortAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
