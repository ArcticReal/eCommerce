package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchResult;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchResult.WorkEffortSearchResult;
public class WorkEffortSearchResultAdded implements Event{

	private WorkEffortSearchResult addedWorkEffortSearchResult;
	private boolean success;

	public WorkEffortSearchResultAdded(WorkEffortSearchResult addedWorkEffortSearchResult, boolean success){
		this.addedWorkEffortSearchResult = addedWorkEffortSearchResult;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortSearchResult getAddedWorkEffortSearchResult() {
		return addedWorkEffortSearchResult;
	}

}
