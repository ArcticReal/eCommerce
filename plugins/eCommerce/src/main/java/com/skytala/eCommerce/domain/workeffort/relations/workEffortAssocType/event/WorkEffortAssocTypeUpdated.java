package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.model.WorkEffortAssocType;
public class WorkEffortAssocTypeUpdated implements Event{

	private boolean success;

	public WorkEffortAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
