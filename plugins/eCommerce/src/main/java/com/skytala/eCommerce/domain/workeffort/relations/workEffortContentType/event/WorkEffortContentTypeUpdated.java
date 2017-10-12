package com.skytala.eCommerce.domain.workeffort.relations.workEffortContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContentType.model.WorkEffortContentType;
public class WorkEffortContentTypeUpdated implements Event{

	private boolean success;

	public WorkEffortContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
