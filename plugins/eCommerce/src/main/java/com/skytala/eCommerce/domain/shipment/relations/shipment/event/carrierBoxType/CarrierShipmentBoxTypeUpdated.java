package com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierBoxType.CarrierShipmentBoxType;
public class CarrierShipmentBoxTypeUpdated implements Event{

	private boolean success;

	public CarrierShipmentBoxTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
