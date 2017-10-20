package com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierBoxType.CarrierShipmentBoxType;
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
