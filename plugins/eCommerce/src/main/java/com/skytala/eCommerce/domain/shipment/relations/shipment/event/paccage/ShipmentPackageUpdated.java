package com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.paccage.ShipmentPackage;
public class ShipmentPackageUpdated implements Event{

	private boolean success;

	public ShipmentPackageUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
