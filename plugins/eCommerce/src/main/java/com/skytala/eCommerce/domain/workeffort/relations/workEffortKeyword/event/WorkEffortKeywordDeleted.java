package com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.model.WorkEffortKeyword;
public class WorkEffortKeywordDeleted implements Event{

	private boolean success;

	public WorkEffortKeywordDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
