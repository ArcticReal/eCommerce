package com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.model.OrderAdjustmentBilling;
public class OrderAdjustmentBillingUpdated implements Event{

	private boolean success;

	public OrderAdjustmentBillingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
