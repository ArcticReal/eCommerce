package com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageRouteSeg.ShipmentPackageRouteSeg;
public class ShipmentPackageRouteSegFound implements Event{

	private List<ShipmentPackageRouteSeg> shipmentPackageRouteSegs;

	public ShipmentPackageRouteSegFound(List<ShipmentPackageRouteSeg> shipmentPackageRouteSegs) {
		this.shipmentPackageRouteSegs = shipmentPackageRouteSegs;
	}

	public List<ShipmentPackageRouteSeg> getShipmentPackageRouteSegs()	{
		return shipmentPackageRouteSegs;
	}

}
