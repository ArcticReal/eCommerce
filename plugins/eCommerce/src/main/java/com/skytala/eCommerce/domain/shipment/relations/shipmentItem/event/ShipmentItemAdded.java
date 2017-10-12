package com.skytala.eCommerce.domain.shipment.relations.shipmentItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.model.ShipmentItem;
public class ShipmentItemAdded implements Event{

	private ShipmentItem addedShipmentItem;
	private boolean success;

	public ShipmentItemAdded(ShipmentItem addedShipmentItem, boolean success){
		this.addedShipmentItem = addedShipmentItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentItem getAddedShipmentItem() {
		return addedShipmentItem;
	}

}
