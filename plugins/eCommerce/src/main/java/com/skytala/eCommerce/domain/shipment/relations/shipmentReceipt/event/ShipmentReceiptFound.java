package com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.model.ShipmentReceipt;
public class ShipmentReceiptFound implements Event{

	private List<ShipmentReceipt> shipmentReceipts;

	public ShipmentReceiptFound(List<ShipmentReceipt> shipmentReceipts) {
		this.shipmentReceipts = shipmentReceipts;
	}

	public List<ShipmentReceipt> getShipmentReceipts()	{
		return shipmentReceipts;
	}

}
