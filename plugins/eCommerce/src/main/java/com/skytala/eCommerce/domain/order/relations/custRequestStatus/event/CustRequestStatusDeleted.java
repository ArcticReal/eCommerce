package com.skytala.eCommerce.domain.order.relations.custRequestStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestStatus.model.CustRequestStatus;
public class CustRequestStatusDeleted implements Event{

	private boolean success;

	public CustRequestStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
