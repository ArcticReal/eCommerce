package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.model.CarrierShipmentMethod;
public class CarrierShipmentMethodFound implements Event{

	private List<CarrierShipmentMethod> carrierShipmentMethods;

	public CarrierShipmentMethodFound(List<CarrierShipmentMethod> carrierShipmentMethods) {
		this.carrierShipmentMethods = carrierShipmentMethods;
	}

	public List<CarrierShipmentMethod> getCarrierShipmentMethods()	{
		return carrierShipmentMethods;
	}

}
