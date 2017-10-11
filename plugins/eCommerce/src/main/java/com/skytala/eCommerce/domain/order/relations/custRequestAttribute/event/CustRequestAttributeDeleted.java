package com.skytala.eCommerce.domain.order.relations.custRequestAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestAttribute.model.CustRequestAttribute;
public class CustRequestAttributeDeleted implements Event{

	private boolean success;

	public CustRequestAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
