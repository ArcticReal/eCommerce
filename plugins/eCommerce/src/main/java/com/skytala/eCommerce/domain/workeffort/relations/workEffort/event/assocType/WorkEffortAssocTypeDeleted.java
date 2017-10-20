package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocType.WorkEffortAssocType;
public class WorkEffortAssocTypeDeleted implements Event{

	private boolean success;

	public WorkEffortAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
