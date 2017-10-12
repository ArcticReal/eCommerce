package com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.model.ShipmentPackage;
public class ShipmentPackageDeleted implements Event{

	private boolean success;

	public ShipmentPackageDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
