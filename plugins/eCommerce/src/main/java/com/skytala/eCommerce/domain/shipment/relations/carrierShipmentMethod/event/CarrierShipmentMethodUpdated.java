package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.model.CarrierShipmentMethod;
public class CarrierShipmentMethodUpdated implements Event{

	private boolean success;

	public CarrierShipmentMethodUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
