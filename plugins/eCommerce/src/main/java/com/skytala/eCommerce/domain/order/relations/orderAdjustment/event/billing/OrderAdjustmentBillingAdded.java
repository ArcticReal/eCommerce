package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.billing.OrderAdjustmentBilling;
public class OrderAdjustmentBillingAdded implements Event{

	private OrderAdjustmentBilling addedOrderAdjustmentBilling;
	private boolean success;

	public OrderAdjustmentBillingAdded(OrderAdjustmentBilling addedOrderAdjustmentBilling, boolean success){
		this.addedOrderAdjustmentBilling = addedOrderAdjustmentBilling;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderAdjustmentBilling getAddedOrderAdjustmentBilling() {
		return addedOrderAdjustmentBilling;
	}

}
