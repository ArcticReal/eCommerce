package com.skytala.eCommerce.domain.order.relations.custRequest.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.content.CustRequestContent;
public class CustRequestContentUpdated implements Event{

	private boolean success;

	public CustRequestContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
