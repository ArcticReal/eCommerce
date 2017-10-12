package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.model.CarrierShipmentMethod;
public class CarrierShipmentMethodAdded implements Event{

	private CarrierShipmentMethod addedCarrierShipmentMethod;
	private boolean success;

	public CarrierShipmentMethodAdded(CarrierShipmentMethod addedCarrierShipmentMethod, boolean success){
		this.addedCarrierShipmentMethod = addedCarrierShipmentMethod;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CarrierShipmentMethod getAddedCarrierShipmentMethod() {
		return addedCarrierShipmentMethod;
	}

}
