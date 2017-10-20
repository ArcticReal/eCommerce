package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchResult;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchResult.WorkEffortSearchResult;
public class WorkEffortSearchResultUpdated implements Event{

	private boolean success;

	public WorkEffortSearchResultUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
