package com.skytala.eCommerce.domain.workEffortSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortSearchResult.model.WorkEffortSearchResult;
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