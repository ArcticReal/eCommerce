package com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.model.WorkEffortKeyword;
public class WorkEffortKeywordUpdated implements Event{

	private boolean success;

	public WorkEffortKeywordUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
