package com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageRouteSeg.ShipmentPackageRouteSeg;
public class ShipmentPackageRouteSegUpdated implements Event{

	private boolean success;

	public ShipmentPackageRouteSegUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}