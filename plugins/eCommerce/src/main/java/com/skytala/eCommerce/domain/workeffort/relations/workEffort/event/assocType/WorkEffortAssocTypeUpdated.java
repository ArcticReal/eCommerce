package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocType.WorkEffortAssocType;
public class WorkEffortAssocTypeUpdated implements Event{

	private boolean success;

	public WorkEffortAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
