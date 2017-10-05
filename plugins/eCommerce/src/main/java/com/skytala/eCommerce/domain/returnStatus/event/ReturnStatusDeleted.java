package com.skytala.eCommerce.domain.returnStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnStatus.model.ReturnStatus;
public class ReturnStatusDeleted implements Event{

	private boolean success;

	public ReturnStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
