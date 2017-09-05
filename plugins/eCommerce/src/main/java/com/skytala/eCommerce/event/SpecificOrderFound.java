package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Order;

public class SpecificOrderFound implements Event {

	Order order;

	public SpecificOrderFound(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
