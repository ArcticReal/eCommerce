package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.model.WorkEffortAssoc;
public class WorkEffortAssocUpdated implements Event{

	private boolean success;

	public WorkEffortAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
