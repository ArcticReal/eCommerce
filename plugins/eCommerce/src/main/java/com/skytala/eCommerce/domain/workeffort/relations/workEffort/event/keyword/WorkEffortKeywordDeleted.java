package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.keyword.WorkEffortKeyword;
public class WorkEffortKeywordDeleted implements Event{

	private boolean success;

	public WorkEffortKeywordDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
