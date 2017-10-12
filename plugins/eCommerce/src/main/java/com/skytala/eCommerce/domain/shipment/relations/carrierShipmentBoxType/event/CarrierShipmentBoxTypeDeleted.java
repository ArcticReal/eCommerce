package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.model.CarrierShipmentBoxType;
public class CarrierShipmentBoxTypeDeleted implements Event{

	private boolean success;

	public CarrierShipmentBoxTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
