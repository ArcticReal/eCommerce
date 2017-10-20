package com.skytala.eCommerce.domain.order.relations.orderType.event.attr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderType.model.attr.OrderTypeAttr;
public class OrderTypeAttrUpdated implements Event{

	private boolean success;

	public OrderTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
