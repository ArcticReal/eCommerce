package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.model.WorkEffortSearchResult;
public class WorkEffortSearchResultFound implements Event{

	private List<WorkEffortSearchResult> workEffortSearchResults;

	public WorkEffortSearchResultFound(List<WorkEffortSearchResult> workEffortSearchResults) {
		this.workEffortSearchResults = workEffortSearchResults;
	}

	public List<WorkEffortSearchResult> getWorkEffortSearchResults()	{
		return workEffortSearchResults;
	}

}
