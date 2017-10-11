package com.skytala.eCommerce.domain.order.relations.orderTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTypeAttr.model.OrderTypeAttr;
public class OrderTypeAttrUpdated implements Event{

	private boolean success;

	public OrderTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
