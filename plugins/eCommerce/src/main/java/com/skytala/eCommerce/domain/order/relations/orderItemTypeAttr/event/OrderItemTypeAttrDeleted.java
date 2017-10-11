package com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.model.OrderItemTypeAttr;
public class OrderItemTypeAttrDeleted implements Event{

	private boolean success;

	public OrderItemTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
