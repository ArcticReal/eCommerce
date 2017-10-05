package com.skytala.eCommerce.domain.workEffortPurposeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortPurposeType.model.WorkEffortPurposeType;
public class WorkEffortPurposeTypeDeleted implements Event{

	private boolean success;

	public WorkEffortPurposeTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
