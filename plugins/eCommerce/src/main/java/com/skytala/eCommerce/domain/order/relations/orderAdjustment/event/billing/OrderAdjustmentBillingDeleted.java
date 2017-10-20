package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.billing.OrderAdjustmentBilling;
public class OrderAdjustmentBillingDeleted implements Event{

	private boolean success;

	public OrderAdjustmentBillingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
