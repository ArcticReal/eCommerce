package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.model.ShipmentPackageRouteSeg;
public class ShipmentPackageRouteSegUpdated implements Event{

	private boolean success;

	public ShipmentPackageRouteSegUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
