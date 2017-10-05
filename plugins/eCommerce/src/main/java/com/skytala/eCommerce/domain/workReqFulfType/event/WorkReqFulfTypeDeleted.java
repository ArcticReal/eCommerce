package com.skytala.eCommerce.domain.workReqFulfType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workReqFulfType.model.WorkReqFulfType;
public class WorkReqFulfTypeDeleted implements Event{

	private boolean success;

	public WorkReqFulfTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
