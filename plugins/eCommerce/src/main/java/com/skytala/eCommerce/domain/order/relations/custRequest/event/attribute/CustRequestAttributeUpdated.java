package com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute.CustRequestAttribute;
public class CustRequestAttributeUpdated implements Event{

	private boolean success;

	public CustRequestAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
