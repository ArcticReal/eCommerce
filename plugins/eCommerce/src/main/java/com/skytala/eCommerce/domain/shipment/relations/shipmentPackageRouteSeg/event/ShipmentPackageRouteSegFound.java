package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.model.ShipmentPackageRouteSeg;
public class ShipmentPackageRouteSegFound implements Event{

	private List<ShipmentPackageRouteSeg> shipmentPackageRouteSegs;

	public ShipmentPackageRouteSegFound(List<ShipmentPackageRouteSeg> shipmentPackageRouteSegs) {
		this.shipmentPackageRouteSegs = shipmentPackageRouteSegs;
	}

	public List<ShipmentPackageRouteSeg> getShipmentPackageRouteSegs()	{
		return shipmentPackageRouteSegs;
	}

}
