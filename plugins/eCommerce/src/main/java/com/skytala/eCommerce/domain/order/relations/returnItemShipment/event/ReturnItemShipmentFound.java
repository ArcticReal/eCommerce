package com.skytala.eCommerce.domain.order.relations.returnItemShipment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemShipment.model.ReturnItemShipment;
public class ReturnItemShipmentFound implements Event{

	private List<ReturnItemShipment> returnItemShipments;

	public ReturnItemShipmentFound(List<ReturnItemShipment> returnItemShipments) {
		this.returnItemShipments = returnItemShipments;
	}

	public List<ReturnItemShipment> getReturnItemShipments()	{
		return returnItemShipments;
	}

}
