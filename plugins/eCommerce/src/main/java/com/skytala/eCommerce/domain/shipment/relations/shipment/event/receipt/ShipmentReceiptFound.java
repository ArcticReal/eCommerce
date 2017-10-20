package com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt.ShipmentReceipt;
public class ShipmentReceiptFound implements Event{

	private List<ShipmentReceipt> shipmentReceipts;

	public ShipmentReceiptFound(List<ShipmentReceipt> shipmentReceipts) {
		this.shipmentReceipts = shipmentReceipts;
	}

	public List<ShipmentReceipt> getShipmentReceipts()	{
		return shipmentReceipts;
	}

}
