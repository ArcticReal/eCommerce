package com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierMethod.CarrierShipmentMethod;
public class CarrierShipmentMethodFound implements Event{

	private List<CarrierShipmentMethod> carrierShipmentMethods;

	public CarrierShipmentMethodFound(List<CarrierShipmentMethod> carrierShipmentMethods) {
		this.carrierShipmentMethods = carrierShipmentMethods;
	}

	public List<CarrierShipmentMethod> getCarrierShipmentMethods()	{
		return carrierShipmentMethods;
	}

}
