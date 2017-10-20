package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contentType.WorkEffortContentType;
public class WorkEffortContentTypeDeleted implements Event{

	private boolean success;

	public WorkEffortContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
