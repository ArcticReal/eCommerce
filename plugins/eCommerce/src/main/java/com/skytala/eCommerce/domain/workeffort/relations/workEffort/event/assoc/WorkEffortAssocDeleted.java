package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assoc.WorkEffortAssoc;
public class WorkEffortAssocDeleted implements Event{

	private boolean success;

	public WorkEffortAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
