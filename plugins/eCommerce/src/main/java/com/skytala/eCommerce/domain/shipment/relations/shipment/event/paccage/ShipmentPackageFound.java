package com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.paccage.ShipmentPackage;
public class ShipmentPackageFound implements Event{

	private List<ShipmentPackage> shipmentPackages;

	public ShipmentPackageFound(List<ShipmentPackage> shipmentPackages) {
		this.shipmentPackages = shipmentPackages;
	}

	public List<ShipmentPackage> getShipmentPackages()	{
		return shipmentPackages;
	}

}
