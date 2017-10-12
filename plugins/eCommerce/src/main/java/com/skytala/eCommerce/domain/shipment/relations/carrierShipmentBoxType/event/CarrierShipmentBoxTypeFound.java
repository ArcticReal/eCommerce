package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.model.CarrierShipmentBoxType;
public class CarrierShipmentBoxTypeFound implements Event{

	private List<CarrierShipmentBoxType> carrierShipmentBoxTypes;

	public CarrierShipmentBoxTypeFound(List<CarrierShipmentBoxType> carrierShipmentBoxTypes) {
		this.carrierShipmentBoxTypes = carrierShipmentBoxTypes;
	}

	public List<CarrierShipmentBoxType> getCarrierShipmentBoxTypes()	{
		return carrierShipmentBoxTypes;
	}

}
