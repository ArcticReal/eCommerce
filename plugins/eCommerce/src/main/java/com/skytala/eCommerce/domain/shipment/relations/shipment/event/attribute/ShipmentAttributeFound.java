package com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.attribute.ShipmentAttribute;
public class ShipmentAttributeFound implements Event{

	private List<ShipmentAttribute> shipmentAttributes;

	public ShipmentAttributeFound(List<ShipmentAttribute> shipmentAttributes) {
		this.shipmentAttributes = shipmentAttributes;
	}

	public List<ShipmentAttribute> getShipmentAttributes()	{
		return shipmentAttributes;
	}

}
