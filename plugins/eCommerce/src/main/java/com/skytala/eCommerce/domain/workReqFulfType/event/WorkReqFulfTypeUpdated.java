package com.skytala.eCommerce.domain.workReqFulfType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workReqFulfType.model.WorkReqFulfType;
public class WorkReqFulfTypeUpdated implements Event{

	private boolean success;

	public WorkReqFulfTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
