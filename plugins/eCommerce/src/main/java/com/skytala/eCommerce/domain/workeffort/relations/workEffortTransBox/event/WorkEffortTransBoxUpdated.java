package com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.model.WorkEffortTransBox;
public class WorkEffortTransBoxUpdated implements Event{

	private boolean success;

	public WorkEffortTransBoxUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
