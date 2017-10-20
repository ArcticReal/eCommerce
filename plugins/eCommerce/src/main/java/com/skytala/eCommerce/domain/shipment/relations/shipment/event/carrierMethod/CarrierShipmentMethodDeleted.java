package com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierMethod.CarrierShipmentMethod;
public class CarrierShipmentMethodDeleted implements Event{

	private boolean success;

	public CarrierShipmentMethodDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
