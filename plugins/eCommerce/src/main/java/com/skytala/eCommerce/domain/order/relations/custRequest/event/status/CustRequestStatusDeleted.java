package com.skytala.eCommerce.domain.order.relations.custRequest.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.status.CustRequestStatus;
public class CustRequestStatusDeleted implements Event{

	private boolean success;

	public CustRequestStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
