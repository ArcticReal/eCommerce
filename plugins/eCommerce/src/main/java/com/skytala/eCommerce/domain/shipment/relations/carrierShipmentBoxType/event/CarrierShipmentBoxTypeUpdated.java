package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.model.CarrierShipmentBoxType;
public class CarrierShipmentBoxTypeUpdated implements Event{

	private boolean success;

	public CarrierShipmentBoxTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
