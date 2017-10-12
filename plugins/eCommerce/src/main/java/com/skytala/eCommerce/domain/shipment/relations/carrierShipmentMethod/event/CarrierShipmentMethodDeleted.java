package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.model.CarrierShipmentMethod;
public class CarrierShipmentMethodDeleted implements Event{

	private boolean success;

	public CarrierShipmentMethodDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
