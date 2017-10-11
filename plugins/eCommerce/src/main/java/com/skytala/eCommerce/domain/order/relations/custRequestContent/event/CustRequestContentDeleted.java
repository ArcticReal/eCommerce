package com.skytala.eCommerce.domain.order.relations.custRequestContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestContent.model.CustRequestContent;
public class CustRequestContentDeleted implements Event{

	private boolean success;

	public CustRequestContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
