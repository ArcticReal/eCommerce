package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.OrderHeader;

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
