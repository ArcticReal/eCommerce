package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.keyword.WorkEffortKeyword;
public class WorkEffortKeywordUpdated implements Event{

	private boolean success;

	public WorkEffortKeywordUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
