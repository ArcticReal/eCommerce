package com.skytala.eCommerce.domain.order.event;

import java.util.List;

import com.skytala.eCommerce.domain.order.header.OrderHeader;
import com.skytala.eCommerce.framework.pubsub.Event;

public class OrdersFound implements Event {

	private List<OrderHeader> orders;

	public OrdersFound(List<OrderHeader> orders) {
		this.orders = orders;
	}

	public List<OrderHeader> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderHeader> orders) {
		this.orders = orders;
	}

}
