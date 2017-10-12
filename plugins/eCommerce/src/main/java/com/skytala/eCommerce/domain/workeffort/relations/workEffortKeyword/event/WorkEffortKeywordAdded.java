package com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.model.WorkEffortKeyword;
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
