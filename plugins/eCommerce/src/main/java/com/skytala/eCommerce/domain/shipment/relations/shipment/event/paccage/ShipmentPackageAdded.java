package com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.paccage.ShipmentPackage;
public class ShipmentPackageAdded implements Event{

	private ShipmentPackage addedShipmentPackage;
	private boolean success;

	public ShipmentPackageAdded(ShipmentPackage addedShipmentPackage, boolean success){
		this.addedShipmentPackage = addedShipmentPackage;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentPackage getAddedShipmentPackage() {
		return addedShipmentPackage;
	}

}
