package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;
public class OrderSummaryEntryFound implements Event{

	private List<OrderSummaryEntry> orderSummaryEntrys;

	public OrderSummaryEntryFound(List<OrderSummaryEntry> orderSummaryEntrys) {
		this.orderSummaryEntrys = orderSummaryEntrys;
	}

	public List<OrderSummaryEntry> getOrderSummaryEntrys()	{
		return orderSummaryEntrys;
	}

}
