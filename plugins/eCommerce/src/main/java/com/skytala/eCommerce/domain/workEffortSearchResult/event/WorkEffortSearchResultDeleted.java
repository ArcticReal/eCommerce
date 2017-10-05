package com.skytala.eCommerce.domain.workEffortSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortSearchResult.model.WorkEffortSearchResult;
public class WorkEffortSearchResultDeleted implements Event{

	private boolean success;

	public WorkEffortSearchResultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
