package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.keyword.WorkEffortKeyword;
public class WorkEffortKeywordAdded implements Event{

	private WorkEffortKeyword addedWorkEffortKeyword;
	private boolean success;

	public WorkEffortKeywordAdded(WorkEffortKeyword addedWorkEffortKeyword, boolean success){
		this.addedWorkEffortKeyword = addedWorkEffortKeyword;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortKeyword getAddedWorkEffortKeyword() {
		return addedWorkEffortKeyword;
	}

}
