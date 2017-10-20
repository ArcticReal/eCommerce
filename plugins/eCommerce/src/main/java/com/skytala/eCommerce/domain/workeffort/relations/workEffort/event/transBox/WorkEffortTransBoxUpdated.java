package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;
public class WorkEffortTransBoxUpdated implements Event{

	private boolean success;

	public WorkEffortTransBoxUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
