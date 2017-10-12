package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.model.WorkEffortSearchResult;
public class WorkEffortSearchResultDeleted implements Event{

	private boolean success;

	public WorkEffortSearchResultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
