package com.skytala.eCommerce.domain.returnReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnReason.model.ReturnReason;
public class ReturnReasonUpdated implements Event{

	private boolean success;

	public ReturnReasonUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
