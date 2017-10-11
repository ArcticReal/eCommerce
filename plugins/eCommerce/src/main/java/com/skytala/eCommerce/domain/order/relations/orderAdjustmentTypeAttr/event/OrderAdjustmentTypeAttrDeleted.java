package com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.model.OrderAdjustmentTypeAttr;
public class OrderAdjustmentTypeAttrDeleted implements Event{

	private boolean success;

	public OrderAdjustmentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
