package com.skytala.eCommerce.domain.workeffort.relations.workEffortPurposeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortPurposeType.model.WorkEffortPurposeType;
public class WorkEffortPurposeTypeUpdated implements Event{

	private boolean success;

	public WorkEffortPurposeTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
