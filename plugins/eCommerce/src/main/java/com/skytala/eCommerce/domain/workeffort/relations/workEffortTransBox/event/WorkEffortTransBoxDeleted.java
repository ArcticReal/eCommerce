package com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.model.WorkEffortTransBox;
public class WorkEffortTransBoxDeleted implements Event{

	private boolean success;

	public WorkEffortTransBoxDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
