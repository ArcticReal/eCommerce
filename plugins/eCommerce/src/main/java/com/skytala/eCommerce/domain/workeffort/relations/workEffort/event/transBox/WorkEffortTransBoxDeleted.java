package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;
public class WorkEffortTransBoxDeleted implements Event{

	private boolean success;

	public WorkEffortTransBoxDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
