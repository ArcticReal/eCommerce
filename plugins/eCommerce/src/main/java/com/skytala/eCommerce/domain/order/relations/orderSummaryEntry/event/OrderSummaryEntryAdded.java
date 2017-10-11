package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;
public class OrderSummaryEntryAdded implements Event{

	private OrderSummaryEntry addedOrderSummaryEntry;
	private boolean success;

	public OrderSummaryEntryAdded(OrderSummaryEntry addedOrderSummaryEntry, boolean success){
		this.addedOrderSummaryEntry = addedOrderSummaryEntry;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderSummaryEntry getAddedOrderSummaryEntry() {
		return addedOrderSummaryEntry;
	}

}
