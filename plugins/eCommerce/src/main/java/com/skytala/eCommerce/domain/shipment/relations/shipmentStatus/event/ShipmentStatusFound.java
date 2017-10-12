package com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.model.ShipmentStatus;
public class ShipmentStatusFound implements Event{

	private List<ShipmentStatus> shipmentStatuss;

	public ShipmentStatusFound(List<ShipmentStatus> shipmentStatuss) {
		this.shipmentStatuss = shipmentStatuss;
	}

	public List<ShipmentStatus> getShipmentStatuss()	{
		return shipmentStatuss;
	}

}
