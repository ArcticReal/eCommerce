package com.skytala.eCommerce.domain.order.event;

import com.skytala.eCommerce.domain.order.model.Order;
import com.skytala.eCommerce.framework.pubsub.Event;

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
