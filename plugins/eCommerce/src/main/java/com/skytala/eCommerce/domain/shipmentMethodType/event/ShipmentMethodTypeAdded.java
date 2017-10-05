package com.skytala.eCommerce.domain.shipmentMethodType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentMethodType.model.ShipmentMethodType;
public class ShipmentMethodTypeAdded implements Event{

	private ShipmentMethodType addedShipmentMethodType;
	private boolean success;

	public ShipmentMethodTypeAdded(ShipmentMethodType addedShipmentMethodType, boolean success){
		this.addedShipmentMethodType = addedShipmentMethodType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentMethodType getAddedShipmentMethodType() {
		return addedShipmentMethodType;
	}

}
