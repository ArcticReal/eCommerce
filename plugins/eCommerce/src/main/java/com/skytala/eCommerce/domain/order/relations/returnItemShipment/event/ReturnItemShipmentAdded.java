package com.skytala.eCommerce.domain.order.relations.returnItemShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemShipment.model.ReturnItemShipment;
public class ReturnItemShipmentAdded implements Event{

	private ReturnItemShipment addedReturnItemShipment;
	private boolean success;

	public ReturnItemShipmentAdded(ReturnItemShipment addedReturnItemShipment, boolean success){
		this.addedReturnItemShipment = addedReturnItemShipment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnItemShipment getAddedReturnItemShipment() {
		return addedReturnItemShipment;
	}

}
