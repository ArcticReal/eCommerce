package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.model.WorkEffortAssoc;
public class WorkEffortAssocDeleted implements Event{

	private boolean success;

	public WorkEffortAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
