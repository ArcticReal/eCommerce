package com.skytala.eCommerce.domain.shipment.relations.shipment.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.item.ShipmentItem;
public class ShipmentItemFound implements Event{

	private List<ShipmentItem> shipmentItems;

	public ShipmentItemFound(List<ShipmentItem> shipmentItems) {
		this.shipmentItems = shipmentItems;
	}

	public List<ShipmentItem> getShipmentItems()	{
		return shipmentItems;
	}

}
