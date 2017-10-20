package com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.attribute.ShipmentAttribute;
public class ShipmentAttributeAdded implements Event{

	private ShipmentAttribute addedShipmentAttribute;
	private boolean success;

	public ShipmentAttributeAdded(ShipmentAttribute addedShipmentAttribute, boolean success){
		this.addedShipmentAttribute = addedShipmentAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentAttribute getAddedShipmentAttribute() {
		return addedShipmentAttribute;
	}

}
