package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.model.CarrierShipmentBoxType;
public class CarrierShipmentBoxTypeAdded implements Event{

	private CarrierShipmentBoxType addedCarrierShipmentBoxType;
	private boolean success;

	public CarrierShipmentBoxTypeAdded(CarrierShipmentBoxType addedCarrierShipmentBoxType, boolean success){
		this.addedCarrierShipmentBoxType = addedCarrierShipmentBoxType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CarrierShipmentBoxType getAddedCarrierShipmentBoxType() {
		return addedCarrierShipmentBoxType;
	}

}
