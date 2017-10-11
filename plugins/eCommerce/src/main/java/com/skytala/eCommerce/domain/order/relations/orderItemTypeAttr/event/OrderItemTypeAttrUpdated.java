package com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.model.OrderItemTypeAttr;
public class OrderItemTypeAttrUpdated implements Event{

	private boolean success;

	public OrderItemTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
