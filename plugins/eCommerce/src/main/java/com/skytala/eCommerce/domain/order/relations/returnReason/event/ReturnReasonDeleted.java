package com.skytala.eCommerce.domain.order.relations.returnReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnReason.model.ReturnReason;
public class ReturnReasonDeleted implements Event{

	private boolean success;

	public ReturnReasonDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
