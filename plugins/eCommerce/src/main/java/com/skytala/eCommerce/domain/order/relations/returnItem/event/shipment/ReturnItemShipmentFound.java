package com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.shipment.ReturnItemShipment;
public class ReturnItemShipmentFound implements Event{

	private List<ReturnItemShipment> returnItemShipments;

	public ReturnItemShipmentFound(List<ReturnItemShipment> returnItemShipments) {
		this.returnItemShipments = returnItemShipments;
	}

	public List<ReturnItemShipment> getReturnItemShipments()	{
		return returnItemShipments;
	}

}
