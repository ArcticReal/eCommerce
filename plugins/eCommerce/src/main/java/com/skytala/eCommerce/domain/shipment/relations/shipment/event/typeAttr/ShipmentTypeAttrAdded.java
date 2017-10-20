package com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.typeAttr.ShipmentTypeAttr;
public class ShipmentTypeAttrAdded implements Event{

	private ShipmentTypeAttr addedShipmentTypeAttr;
	private boolean success;

	public ShipmentTypeAttrAdded(ShipmentTypeAttr addedShipmentTypeAttr, boolean success){
		this.addedShipmentTypeAttr = addedShipmentTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentTypeAttr getAddedShipmentTypeAttr() {
		return addedShipmentTypeAttr;
	}

}
