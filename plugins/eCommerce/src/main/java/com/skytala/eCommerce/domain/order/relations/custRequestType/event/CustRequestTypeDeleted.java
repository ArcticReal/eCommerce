package com.skytala.eCommerce.domain.order.relations.custRequestType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestType.model.CustRequestType;
public class CustRequestTypeDeleted implements Event{

	private boolean success;

	public CustRequestTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
