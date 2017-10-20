package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.typeAttr.OrderAdjustmentTypeAttr;
public class OrderAdjustmentTypeAttrUpdated implements Event{

	private boolean success;

	public OrderAdjustmentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
