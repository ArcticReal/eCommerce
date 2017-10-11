package com.skytala.eCommerce.domain.order.relations.orderShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;
public class OrderShipmentAdded implements Event{

	private OrderShipment addedOrderShipment;
	private boolean success;

	public OrderShipmentAdded(OrderShipment addedOrderShipment, boolean success){
		this.addedOrderShipment = addedOrderShipment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderShipment getAddedOrderShipment() {
		return addedOrderShipment;
	}

}
