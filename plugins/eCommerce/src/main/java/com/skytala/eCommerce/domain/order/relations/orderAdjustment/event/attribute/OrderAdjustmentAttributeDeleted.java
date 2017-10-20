package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute.OrderAdjustmentAttribute;
public class OrderAdjustmentAttributeDeleted implements Event{

	private boolean success;

	public OrderAdjustmentAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
