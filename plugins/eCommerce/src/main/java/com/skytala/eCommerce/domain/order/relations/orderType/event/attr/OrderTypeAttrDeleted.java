package com.skytala.eCommerce.domain.order.relations.orderType.event.attr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderType.model.attr.OrderTypeAttr;
public class OrderTypeAttrDeleted implements Event{

	private boolean success;

	public OrderTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
