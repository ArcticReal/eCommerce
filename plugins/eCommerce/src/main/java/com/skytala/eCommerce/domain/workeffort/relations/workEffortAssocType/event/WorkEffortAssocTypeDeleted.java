package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.model.WorkEffortAssocType;
public class WorkEffortAssocTypeDeleted implements Event{

	private boolean success;

	public WorkEffortAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
