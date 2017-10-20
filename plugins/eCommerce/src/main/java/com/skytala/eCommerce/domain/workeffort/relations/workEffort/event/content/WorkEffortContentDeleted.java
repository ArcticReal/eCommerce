package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.content.WorkEffortContent;
public class WorkEffortContentDeleted implements Event{

	private boolean success;

	public WorkEffortContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
