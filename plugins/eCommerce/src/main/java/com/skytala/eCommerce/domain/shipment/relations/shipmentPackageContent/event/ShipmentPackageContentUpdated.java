package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.model.ShipmentPackageContent;
public class ShipmentPackageContentUpdated implements Event{

	private boolean success;

	public ShipmentPackageContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
