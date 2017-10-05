package com.skytala.eCommerce.domain.shipment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.model.Shipment;
public class ShipmentFound implements Event{

	private List<Shipment> shipments;

	public ShipmentFound(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public List<Shipment> getShipments()	{
		return shipments;
	}

}
