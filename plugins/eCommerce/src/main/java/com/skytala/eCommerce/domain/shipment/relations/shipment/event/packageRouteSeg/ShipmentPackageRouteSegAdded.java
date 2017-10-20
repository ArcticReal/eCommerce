package com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageRouteSeg.ShipmentPackageRouteSeg;
public class ShipmentPackageRouteSegAdded implements Event{

	private ShipmentPackageRouteSeg addedShipmentPackageRouteSeg;
	private boolean success;

	public ShipmentPackageRouteSegAdded(ShipmentPackageRouteSeg addedShipmentPackageRouteSeg, boolean success){
		this.addedShipmentPackageRouteSeg = addedShipmentPackageRouteSeg;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentPackageRouteSeg getAddedShipmentPackageRouteSeg() {
		return addedShipmentPackageRouteSeg;
	}

}
