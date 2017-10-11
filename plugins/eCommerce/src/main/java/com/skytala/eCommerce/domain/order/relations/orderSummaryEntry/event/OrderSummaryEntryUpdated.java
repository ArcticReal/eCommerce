package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;
public class OrderSummaryEntryUpdated implements Event{

	private boolean success;

	public OrderSummaryEntryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
