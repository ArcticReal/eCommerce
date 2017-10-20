package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.purposeType.WorkEffortPurposeType;
public class WorkEffortPurposeTypeUpdated implements Event{

	private boolean success;

	public WorkEffortPurposeTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
