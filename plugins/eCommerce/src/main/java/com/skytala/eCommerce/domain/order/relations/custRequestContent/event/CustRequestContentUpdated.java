package com.skytala.eCommerce.domain.order.relations.custRequestContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestContent.model.CustRequestContent;
public class CustRequestContentUpdated implements Event{

	private boolean success;

	public CustRequestContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
