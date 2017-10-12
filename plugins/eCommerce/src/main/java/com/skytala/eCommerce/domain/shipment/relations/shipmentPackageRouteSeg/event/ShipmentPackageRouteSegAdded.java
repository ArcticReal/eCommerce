package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.model.ShipmentPackageRouteSeg;
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
