package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.model.ShipmentPackageContent;
public class ShipmentPackageContentAdded implements Event{

	private ShipmentPackageContent addedShipmentPackageContent;
	private boolean success;

	public ShipmentPackageContentAdded(ShipmentPackageContent addedShipmentPackageContent, boolean success){
		this.addedShipmentPackageContent = addedShipmentPackageContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentPackageContent getAddedShipmentPackageContent() {
		return addedShipmentPackageContent;
	}

}
