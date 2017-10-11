package com.skytala.eCommerce.domain.order.relations.orderShipment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;
public class OrderShipmentFound implements Event{

	private List<OrderShipment> orderShipments;

	public OrderShipmentFound(List<OrderShipment> orderShipments) {
		this.orderShipments = orderShipments;
	}

	public List<OrderShipment> getOrderShipments()	{
		return orderShipments;
	}

}
