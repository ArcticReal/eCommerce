package com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.model.WorkEffortContent;
public class WorkEffortContentUpdated implements Event{

	private boolean success;

	public WorkEffortContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
