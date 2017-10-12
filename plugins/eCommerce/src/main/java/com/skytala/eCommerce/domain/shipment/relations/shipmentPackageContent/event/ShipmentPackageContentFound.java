package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.model.ShipmentPackageContent;
public class ShipmentPackageContentFound implements Event{

	private List<ShipmentPackageContent> shipmentPackageContents;

	public ShipmentPackageContentFound(List<ShipmentPackageContent> shipmentPackageContents) {
		this.shipmentPackageContents = shipmentPackageContents;
	}

	public List<ShipmentPackageContent> getShipmentPackageContents()	{
		return shipmentPackageContents;
	}

}
